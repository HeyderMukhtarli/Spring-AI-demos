package com.example.prompts.controller;

import com.example.prompts.Songs;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.parser.ListOutputParser;
import org.springframework.ai.parser.OutputParser;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
public class SongsController {
    private final ChatClient chatClient;

    public SongsController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    @GetMapping("/songs")
    public String getSongsByArtist(@RequestParam(value = "artist", defaultValue = "Ariana Grande") String artist) {
        String message="Please give me a list of top 10 songs for the {artist}.{format}";
        return chatClient.prompt()
                .user(u -> u.text(message).param("artist",artist))
                .call()
                .content();
    }
    @GetMapping("/songs-by-artist")
    public Songs getActorFilmsByName(@RequestParam(value = "artist", defaultValue = "Michael Jackson") String artist) {
        return chatClient.prompt()
                .user(u -> u.text("Generate top 10 songs for the {artist}").param("artist",artist))
                .call()
                .entity(Songs.class);
    }

    @GetMapping("/stream")
    public Flux<String> stream(@RequestParam(
            value = "message",
            defaultValue = "I'm visiting San Francisco next month, what are 10 places I must visit?") String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
