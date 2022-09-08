package com.notification.service.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.service.kafka.exception.MapperException;
import com.notification.service.kafka.model.Notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "notification", groupId = "notification-group-id", containerFactory = "kakfaListenerContainerFactory")
    public void listenSenderEmail(String data) {

        Notification notification = fromJson(data, Notification.class);
        log.info("Consumed message: " + data);
        template.convertAndSend("/topic/notif", notification);

    }

    /**
     * Convert json to Object
     * 
     * @param json  String json value
     * @param clazz Instances of the class
     * @param <T>   Object Class
     * @return Object class
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
