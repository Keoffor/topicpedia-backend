package com.searchtopic.topicpedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchList {
    private String title;
    private long pageid;
    private String snippet;
}
