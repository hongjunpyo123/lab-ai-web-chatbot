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
        return ",사용자 질문:";
    }
}
