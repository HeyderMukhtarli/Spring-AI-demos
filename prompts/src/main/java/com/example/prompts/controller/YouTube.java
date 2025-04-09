package com.example.prompts.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api/youtube")
public class YouTube {
     @Value("classpath:prompts/youtube.st")
    private Resource youtubePrompt;
    private final ChatClient chatClient;


    public YouTube(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/popular")
    public String findPopularYouTubersStepOne(@RequestParam(value = "genre", defaultValue = "tech") String genre) {

//        PromptTemplate promptTemplate = new PromptTemplate(message);
//        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
//        return chatClient.call(prompt).getResult().getOutput().getContent();
        return chatClient.prompt()
                .user(u -> u.text(youtubePrompt).param("genre",genre))
                .call()
                .content();


    }





}