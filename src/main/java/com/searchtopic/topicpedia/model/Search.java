package com.searchtopic.topicpedia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Search {
  @JsonProperty(value = "continue")
  private Continue aContinue;
  private SearchQuery query;
}
