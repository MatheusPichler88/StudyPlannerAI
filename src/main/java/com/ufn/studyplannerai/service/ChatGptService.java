package com.ufn.studyplannerai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final String apiKey = System.getenv("API_KEY");


    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }
}
