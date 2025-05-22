package com.myresearch.assistant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponse {
    private List<Candidate> candidates;

    // Manually created getter for candidates
    public List<Candidate> getCandidates() {
        return candidates;
    }

    // Manually created setter for candidates
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Candidate {
        private Content content;

        // Manually created getter for content
        public Content getContent() {
            return content;
        }

        // Manually created setter for content
        public void setContent(Content content) {
            this.content = content;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {
        private List<Part> parts;

        // Manually created getter for parts
        public List<Part> getParts() {
            return parts;
        }

        // Manually created setter for parts
        public void setParts(List<Part> parts) {
            this.parts = parts;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Part {
        private String text;

        // Manually created getter for text
        public String getText() {
            return text;
        }

        // Manually created setter for text
        public void setText(String text) {
            this.text = text;
        }
    }
}
