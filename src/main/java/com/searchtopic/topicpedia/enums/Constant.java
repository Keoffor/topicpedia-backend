package com.searchtopic.topicpedia.enums;

public class Constant {
    final public static String API_URL = "https://en.wikipedia.org/w/api.php";
    final public static  String TITLE_PARAM1 = "?action=query&format=json&prop=extracts%7Cpageimages&list=&continue=&titles=";
    final public static  String TITLE_PARAM2 ="&formatversion=2&exsentences=7&exlimit=1&explaintext=1&exsectionformat=raw&piprop=thumbnail%7Cname&pithumbsize=100&pilimit=100";
    final public static String SEARCH_PARAM ="?action=query&format=json&prop=info&inprop=url&list=search&formatversion=2&srlimit=4&srsearch=";

}
