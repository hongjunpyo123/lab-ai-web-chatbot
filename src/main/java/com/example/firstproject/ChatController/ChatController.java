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
    private ChatMessage lapdata = new ChatMessage(
            "연구실_이름 : pl 랩실, " +
            "교수:홍길동 교수님, " +
            "연구_분야:[머신러닝, 프로그래밍]" +
            "구성원:[홍길동, 오하준, 김수빈, 정민수, 이지현 등등]");

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
        message = lapdata.getMessage() + "모든 답변은 방금 올린 데이터 안에서만 가능해, 이외의 질문에는 답변 할 수 없어요! 출력, 답변은 최대한 간결하게하고 불필요한 말은 하지마, 사용자 질문 : " + message;
        model.addAttribute("response", chatModel.call(message));
        return "chat";
    }
}
