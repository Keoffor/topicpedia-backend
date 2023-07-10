package com.searchtopic.topicpedia.backendtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchtopic.topicpedia.service.SearchTitleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

@Profile("test")
@ExtendWith(MockitoExtension.class)
public class PagesTest {


    @Mock
    private SearchTitleService searchTitleService;

    @InjectMocks
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnPageContentOfTitleInput(){


        String requestString = objectMapper.readValue(ConstantTest.JSONSTRING_VAR,);
    }
}
