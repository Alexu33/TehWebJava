package com.IstrateCristianAlexandru408.onlineshop.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private List<String> messages;

    public ErrorResponse(int status, String error, List<String> messages) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.messages = messages;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
