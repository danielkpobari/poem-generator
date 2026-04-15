package com.spring_ai.Spring_AI;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PoemPromptTemplate {

    private static final PromptTemplate TEMPLATE =
            new PromptTemplate("""
        Write a {genre} haiku about {theme}.
        Follow 5-7-5 syllable structure.

        Return strictly in JSON:
        {{
          "title": "",
          "content": "",
          "genre": "",
          "theme": ""
        }}
    """);

    public Prompt create(String genre, String theme) {
        return TEMPLATE.create(Map.of(
                "genre", genre,
                "theme", theme
        ));
    }
}
