package com.notification.service.kafka.model;

public class Notification {
    private Long id;
    private String content;
    private NotificationType type;

    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Notification [content=" + content + ", id=" + id + ", type=" + type + "]";
    }

}
