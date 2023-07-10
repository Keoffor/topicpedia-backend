package com.searchtopic.topicpedia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pages {
    private long pageid;
    private String title;
    private String extract;
    @JsonProperty("thumbnail")
    private SearchTitleThumnails thumbnail;
    private String pageimage;
}
