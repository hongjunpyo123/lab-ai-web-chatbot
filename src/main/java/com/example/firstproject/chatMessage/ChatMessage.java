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
        return "모든 답변은 데이터 안에서만 가능해,이외의 질문에는 답변할 수 없다는 내용을 짧게 전달해,답변은 최대한 간결하게하고 불필요한 말은 하지마" +
                "데이터를 나열하지 말고 간결하게 설명해,사용자 질문:";
    }
}
