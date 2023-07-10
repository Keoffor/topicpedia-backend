package com.searchtopic.topicpedia.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchtopic.topicpedia.dto.SearchResponse;
import com.searchtopic.topicpedia.dto.SearchTitleResponse;
import com.searchtopic.topicpedia.enums.Constant;
import com.searchtopic.topicpedia.exception.ResourceEmptyException;
import com.searchtopic.topicpedia.model.Search;
import com.searchtopic.topicpedia.model.SearchList;
import com.searchtopic.topicpedia.model.SearchTitle;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchTopicService {
    private final Logger log = LoggerFactory.getLogger(SearchTopicService.class);
    @Autowired
    private SearchTitleService searchTitleService;

    public List<SearchResponse> searchTopic(String topic) throws ResourceEmptyException {
        SearchResponse searchResponses = new SearchResponse();
        List<SearchResponse> result = null;
        String encodedTopic = URLEncoder.encode(topic, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(Constant.API_URL+Constant.SEARCH_PARAM+encodedTopic))
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //convert jsonString to jsonObject and store user data to database.
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Search search = objectMapper.readValue(response.body(), Search.class);
            result = maptoSearch(search);

        }catch (Exception ex) {
            log.error("an error occurred while retrieving data", ex);
            throw new ResourceEmptyException("Unable to fetch content");
        }
        return result;
    }


    private List<SearchResponse> maptoSearch(Search search){
        List<SearchResponse> result = new ArrayList<>();
        if(search!=null){
            search.getQuery().getSearch().forEach(res -> {
                SearchResponse response1 = new SearchResponse();
                String snippet = cleanSnippet(res.getSnippet());
                response1.setSnippet(snippet);
                response1.setTitle(res.getTitle());
                response1.setPageid(res.getPageid());
                result.add(response1);
            });
        }else {
                log.error("Search result is empty");
            }
            return result;
        }
    private String cleanSnippet(String snippet) {
        snippet = snippet.replaceAll("<[^>]+>", ""); // Remove HTML tags
        snippet = snippet.replaceAll("\\s+", " "); // Replace multiple spaces with a single space
        snippet = snippet.replaceAll("\\\\n", " "); // Replace line breaks with a space
        return snippet.trim();
    }

    public SearchTitleResponse getTopicsByTitle(String title) throws ResourceEmptyException {
        return searchTitleService.searchTitle(title);
    }
}
