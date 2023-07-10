package com.searchtopic.topicpedia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchTitleQuery {
 @JsonProperty(value = "pages")
 private List<Pages> pages;
}
