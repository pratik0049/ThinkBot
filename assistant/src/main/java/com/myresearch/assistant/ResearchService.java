package com.myresearch.assistant;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ResearchService {

    @Value("${gemini.api.url}")
    private String geminiAPIUrl;

    @Value("${gemini.api.key}")
    private String geminiAPIKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder
                .baseUrl(geminiAPIUrl)
                .build();
        this.objectMapper = objectMapper;
    }

    public String processContent(ResearchRequest req) {
        String prompt = buildPrompt(req);

        // Create request payload
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        try {
            // Properly construct the URL with the API key as a query parameter
            String fullUrl = UriComponentsBuilder.fromHttpUrl(geminiAPIUrl)
                    .queryParam("key", geminiAPIKey)
                    .toUriString();

            return webClient.post()
                    .uri(fullUrl) // Use the fully constructed URL
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(this::extractTextFromResponse)
                    .onErrorResume(e -> Mono.just("Error calling Gemini API: " + e.getMessage()))
                    .block();

        } catch (Exception e) {
            return "Request failed: " + e.getMessage();
        }
    }

    private String extractTextFromResponse(String response) {
        try {
            GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);

            if (geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()) {
                GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);

                if (firstCandidate.getContent() != null &&
                        firstCandidate.getContent().getParts() != null &&
                        !firstCandidate.getContent().getParts().isEmpty()) {

                    return firstCandidate.getContent().getParts().get(0).getText();
                }
            }

            return "No valid response from Gemini API.";

        } catch (Exception e) {
            return "Error Parsing Response: " + e.getMessage();
        }
    }

    private String buildPrompt(ResearchRequest req) {
        StringBuilder prompt = new StringBuilder();

        switch (req.getOperation()) {
        case "summarize":
            prompt.append("Extract the most important points and key insights from the following text. Provide a concise summary that captures the essence of the content while preserving its main ideas.\n\n");
            break;
        case "suggest":
            prompt.append("Analyze the following content and suggest the most relevant topics, key takeaways, and further readings. Focus on insightful and valuable recommendations.\n\n");
            break;
        default:
            throw new IllegalArgumentException("Unknown Operation: " + req.getOperation());
    }


        prompt.append(req.getContent());
        return prompt.toString();
    }
}
