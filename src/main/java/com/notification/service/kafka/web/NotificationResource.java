package com.notification.service.kafka.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.service.kafka.model.Notification;
import com.notification.service.kafka.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationResource {

    private final NotificationService notificationService;

    public NotificationResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotification(@RequestBody Notification notification) {
        notificationService.send(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
