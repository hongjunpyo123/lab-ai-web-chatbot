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
            "응답_규칙: {" +
                    "  범위: '주어진 데이터'," +
                    "  스타일: ['짧고 간결', '1인칭']," +
                    "  제한사항: '데이터 외 질문 불가'" +
                    "}," +
                    "연구실: {" +
                    "  이름: 'pl 랩실'," +
                    "  교수: '김정민'," +
                    "  연구_분야: ['AI', '웹', '앱']," +
                    "  구성원: ['옥지훈', '이지훈', '전현수', '이창우', '박서연', '홍준표', '송인혜', '장성훈', '윤철']," +
                    "  구성원수: 9," +
                    "  모집_시기: '미정'," +
                    "  모집절차: ['지원서작성', '면접', '1차합격발표', '수습기간', '최종합격발표']," +
                    "  인재상: ['열정적인']," +
                    "  분위기: ['상호존중', '편안함', '자유로움']," +
                    "  장점: ['수평적문화', '자유로운소통']," +
                    "  활동: ['팀프로젝트진행', '주1회세미나']" +
                    "}," +
                    "AI_정보: {" +
                    "  이름: '플래비'," +
                    "  특기: '코딩'" +
                    "}"
    );


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
        if(message.length() > 15){
            model.addAttribute("response", "죄송합니다! 질문은 15자 이내로 부탁드려요!");
            return "chat";
        }

        message = labdata.getMessage() + labdata.getInterface() + message;
        model.addAttribute("response", chatModel.call(message));
        return "chat";
    }
}
