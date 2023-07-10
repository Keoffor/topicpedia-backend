package com.searchtopic.topicpedia.backendtest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchtopic.topicpedia.dto.SearchResponse;
import com.searchtopic.topicpedia.dto.SearchTitleResponse;
import com.searchtopic.topicpedia.exception.ResourceEmptyException;
import com.searchtopic.topicpedia.model.Search;
import com.searchtopic.topicpedia.model.SearchTitle;
import com.searchtopic.topicpedia.service.SearchTitleService;
import com.searchtopic.topicpedia.service.SearchTopicService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SearchTest {
    @Mock
    private HttpClient httpClient;
    @InjectMocks
    private SearchTitleService searchTitleService;

    @InjectMocks
    private SearchTopicService searchTopicService;



    @Test
    void SearchTitleMethodTest() throws Exception {
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        // Create a mock response
        Mockito.lenient().when(mockResponse.body()).thenReturn(ConstantTest.JSONSTRING_VAR);

        // Mock the send method of the HttpClient to return the mock response
        Mockito.lenient().when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        // Call the searchTitle method and assert the expected result
        SearchTitleResponse result = searchTitleService.searchTitle("Wizkid");

        Assertions.assertEquals("Wizkid", result.getTitle());
        Assertions.assertEquals(39576371, result.getPageid());
    }


    @Test
    void SearchTopicMethodTest() throws Exception {
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        // Create a mock response
        Mockito.lenient().when(mockResponse.body()).thenReturn(ConstantTest.JSONSTRING_VAR);

        // Mock the send method of the HttpClient to return the mock response
        Mockito.lenient().when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        // Call the searchTitle method and assert the expected result
        List<SearchResponse> result = searchTopicService.searchTopic("Wizkid");

        Assertions.assertEquals("Wizkid", result.get(0).getTitle());
        Assertions.assertEquals(39576371, result.get(0).getPageid());
    }
}
