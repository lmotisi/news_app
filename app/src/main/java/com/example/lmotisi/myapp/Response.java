package com.example.lmotisi.myapp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Response
{
    @JsonProperty("status")
    String status;

    @JsonProperty("posts")
    ArrayList<Post> posts;

}
