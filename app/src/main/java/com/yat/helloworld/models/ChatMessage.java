package com.yat.helloworld.models;

public class ChatMessage {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String messageText;
    private String messageUser;
    private long messageTime;
    private String imageUrl;


    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ChatMessage(String messageText, String messageUser, String imageUrl) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.imageUrl= imageUrl;
        messageTime = (int)(System.currentTimeMillis()/1000);

    }

    public ChatMessage() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }
}
