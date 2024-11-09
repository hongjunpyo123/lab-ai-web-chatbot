package com.example.firstproject.chatMessage;

import lombok.Getter;
import lombok.Setter;

public class ChatMessage {
    private String chatMessage;


    public ChatMessage(String message){
        this.chatMessage = message;
    }

    public void setMessage(String message){
        this.chatMessage = message;
    }

    public String getMessage(){
        return this.chatMessage;
    }

    public String getInterface(){
        return "모든 답변은 방금 올린 데이터 안에서만 가능해, 이외의 질문에는 답변 할 수 없어요! 출력, 답변은 최대한 간결하게하고 불필요한 말은 하지마, 모든 답변은 존대로 해, 사용자 질문:";
    }
}
