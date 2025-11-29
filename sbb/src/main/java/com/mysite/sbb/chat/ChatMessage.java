package com.mysite.sbb.chat;

public class ChatMessage {
    private String from;     // 누가 보냈는지
    private String to;       // (나중에) 누구에게 보낼지
    private String content;  // 메시지 내용

    public ChatMessage() {}

    public ChatMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
