package com.searchtopic.topicpedia.controller;


import com.searchtopic.topicpedia.dto.SearchResponse;
import com.searchtopic.topicpedia.dto.SearchTitleResponse;
import com.searchtopic.topicpedia.exception.ResourceEmptyException;
import com.searchtopic.topicpedia.model.Search;
import com.searchtopic.topicpedia.service.SearchTitleService;
import com.searchtopic.topicpedia.service.SearchTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class SearchTopicController {

   final private SearchTitleService searchTitleService;
   final private SearchTopicService searchService;

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchResponse> getAllSearch(@RequestParam String topic) throws ResourceEmptyException {
    return searchService.searchTopic(topic);
    }
    @GetMapping(value = "/page")
    @ResponseStatus(HttpStatus.OK)
    public SearchTitleResponse getTopicByTitle(@RequestParam String title) throws ResourceEmptyException {
        return  searchService.getTopicsByTitle(title);

    }
}
