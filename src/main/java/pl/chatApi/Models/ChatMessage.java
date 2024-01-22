package pl.chatApi.Models;

public class ChatMessage {

    private String value;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ChatMessage(String value, String test_message) {
        this.value = value;
    }

    public ChatMessage() {
    }
}