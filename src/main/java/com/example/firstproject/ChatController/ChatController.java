package com.example.firstproject.ChatController;

import com.example.firstproject.Dto;
import com.example.firstproject.chatMessage.ChatMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@Controller
public class ChatController {

    private final AnthropicChatModel chatModel;
    private ChatMessage labdata = new ChatMessage(
            "연구실_이름 : pl 랩실, " +
            "교수:김정민 교수님, " +
            "연구_분야:[머신러닝, 프로그래밍]" +
            "구성원:[옥지훈(랩장), 이지훈(랩원), 전현수(랩원), 이창우(랩원), 박서연(랩원), 홍준표(랩원)]" +
                    "[송인혜(랩원), 장성훈(랩원), 윤철(랩원)]" +
            "모집_시기:미정" +
            "구성원_수:9" +
            "모집절차:1.지원서작성/2.면접/3.1차합격발표/4.수습기간/5.최종합격발표");

    @Autowired
    public ChatController(AnthropicChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/")
    public String enter(){
        return "chat";
    }
    @GetMapping("/ai")
    public String generate(@RequestParam String message, Model model) {
        if(message.isEmpty()){
            model.addAttribute("response", "질문 주시면 성실히 답변드릴께요!");
            return "chat";
        }
        message = labdata.getMessage() + labdata.getInterface() + message;
        model.addAttribute("response", chatModel.call(message));
        return "chat";
    }
}
