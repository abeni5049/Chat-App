package com.example.chatapp;

import java.util.HashMap;

public class Chats {

    private HashMap<String,String> messages;
    private String username;

    public Chats() {
    }

    public Chats(HashMap<String,String> messages, String username) {
        this.messages = messages;
        this.username = username;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String,String> messages) {
        this.messages = messages;
    }

    public String getUsername() {
        return username;
    }

    public void setSender(String username) {
        this.username = username;
    }

}