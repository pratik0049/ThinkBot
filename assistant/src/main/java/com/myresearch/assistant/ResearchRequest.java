package com.myresearch.assistant;

import lombok.Data;

@Data
public class ResearchRequest {

    private String content;
    private String operation;

    public String getOperation() {
       return this.operation;
    }
    public String getContent() {
        return this.content;
    }
}