package com.notification.service.kafka.service;

import com.notification.service.kafka.model.Notification;

public interface NotificationService {

    /**
     * Send notification
     * 
     * @param notification model of notification
     */
    void send(Notification notification);
}
