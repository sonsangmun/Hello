package com.example.smson.hello.chat.client;

/**
 * Created by sangmun on 2015-04-21.
 */
public class Notice implements ChatHistory {
    String message;

    public Notice(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
