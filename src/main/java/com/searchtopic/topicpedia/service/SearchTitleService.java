package com.searchtopic.topicpedia.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchtopic.topicpedia.dto.SearchTitleResponse;
import com.searchtopic.topicpedia.enums.Constant;
import com.searchtopic.topicpedia.exception.ResourceEmptyException;
import com.searchtopic.topicpedia.model.SearchTitle;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SearchTitleService {

 private final Logger log = LoggerFactory.getLogger(SearchTitleService.class);


    /**
     * Returns the page with this title, else returns no page content found.
     * @param title The topic to search for.
     * @return The page with the input topic, else returns a no page content found error.
     * @throws ResourceEmptyException if page title request cannot be fetched due to server problem
     */
    public SearchTitleResponse searchTitle(String title) throws ResourceEmptyException {
        SearchTitleResponse searchTitleResponse = null;

        String encodedTopic = URLEncoder.encode(title, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(Constant.API_URL+Constant.TITLE_PARAM1+encodedTopic+Constant.TITLE_PARAM2))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
                    try {
                        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                            //convert jsonString to jsonObject and store user data to database.
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                            SearchTitle searchTitle = objectMapper.readValue(response.body(), SearchTitle.class);
                            if(searchTitle.getQuery().getPages().get(0).getExtract()!=null &&
                               searchTitle.getQuery().getPages().get(0).getThumbnail()!=null){

                               searchTitleResponse = maptoSearchTitleWithThumbnails(searchTitle);
                            } else if (searchTitle.getQuery().getPages().get(0).getExtract() != null &&
                                searchTitle.getQuery().getPages().get(0).getThumbnail()==null){
                                searchTitleResponse = maptoSearchTitleWithoutThumbnails(searchTitle);

                            } else{
                                log.error("Resource is empty {}", response);
                                throw new ResourceEmptyException("No page content found");

                            }
                    }catch (Exception ex) {
                        log.error("an error occurred while retrieving data", ex);
                        throw new ResourceEmptyException("Unable to fetch content");

                    }
                    return searchTitleResponse;
                }



    public SearchTitleResponse maptoSearchTitleWithThumbnails(SearchTitle searchTitle){
        SearchTitleResponse topicResponse = new SearchTitleResponse();
        topicResponse.setPageid(searchTitle.getQuery().getPages().get(0).getPageid());
        topicResponse.setExtracts(searchTitle.getQuery().getPages().get(0).getExtract());
        topicResponse.setTitle(searchTitle.getQuery().getPages().get(0).getTitle());
        topicResponse.setSource(searchTitle.getQuery().getPages().get(0).getThumbnail().getSource());
        topicResponse.setPageimage(searchTitle.getQuery().getPages().get(0).getPageimage());
        return topicResponse;
    }
    public SearchTitleResponse maptoSearchTitleWithoutThumbnails(SearchTitle searchTitle){
        SearchTitleResponse topicResponse = new SearchTitleResponse();
        topicResponse.setPageid(searchTitle.getQuery().getPages().get(0).getPageid());
        topicResponse.setExtracts(searchTitle.getQuery().getPages().get(0).getExtract());
        topicResponse.setTitle(searchTitle.getQuery().getPages().get(0).getTitle());
        topicResponse.setPageimage(searchTitle.getQuery().getPages().get(0).getPageimage());
        return topicResponse;
    }
}