package com.spring_ai.Spring_AI;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class PoetryServiceImpl implements PoetryService {

    private final ChatClient chatClient;
    private final PoemPromptTemplate promptTemplate;

    public PoetryServiceImpl(ChatClient chatClient,
                             PoemPromptTemplate promptTemplate) {
        this.chatClient = chatClient;
        this.promptTemplate = promptTemplate;
    }

    @Override
    public Poem generate(String genre, String theme) {

        var prompt = promptTemplate.create(genre, theme);

        return chatClient
                .prompt(prompt)
                .call()
                .entity(Poem.class);
    }
}
