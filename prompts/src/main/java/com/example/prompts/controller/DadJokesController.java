package com.example.prompts.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dadjokes")
public class DadJokesController {
    private final ChatClient chatClient;


    public DadJokesController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    @GetMapping()
    public String jokes() {
        // OpenAI chat requires system and user messages to guide the conversation
        var system = new SystemMessage("Your primary function is telling dad jokes. If someone asks you to tell another type of joke, you should tell them you only know a dad joke.");
        var user = new UserMessage("Tell me a serious joke about the universe");

        // Creating the Prompt with system and user messages
        Prompt prompt = new Prompt(List.of(system, user));

        // Call OpenAI API with the prompt
      return  chatClient.prompt(prompt).call().content();}
    }