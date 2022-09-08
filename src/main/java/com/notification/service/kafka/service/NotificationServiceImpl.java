package com.notification.service.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.service.kafka.exception.MapperException;
import com.notification.service.kafka.model.Notification;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final BrokerProducerService brokerProducerService;
    private final Environment env;

    public NotificationServiceImpl(BrokerProducerService brokerProducerService, Environment env) {
        this.brokerProducerService = brokerProducerService;
        this.env = env;
    }

    @Override
    public void send(Notification notification) {
        brokerProducerService.sendMessage(env.getProperty("producer.kafka.topic-name"), toJson(notification));
    }

    /**
     * Convert Object to json
     *
     * @param object object
     * @return string json
     */
    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
