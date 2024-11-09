package com.example.firstproject.ChatController;

import com.example.firstproject.Dto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ChatController {

    private final AnthropicChatModel chatModel;

    @Autowired
    public ChatController(AnthropicChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/ai")
    public Dto generate(@RequestParam String message, Model model) {
        Dto dto = new Dto();
        dto.setReponse_message(chatModel.call(message));

        return dto;
    }
}
