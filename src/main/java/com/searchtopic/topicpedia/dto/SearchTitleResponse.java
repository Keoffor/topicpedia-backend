package com.searchtopic.topicpedia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchTitleResponse {
    private long pageid;
    private String title;
    private String extracts;
    private String source;
    private String pageimage;
}
