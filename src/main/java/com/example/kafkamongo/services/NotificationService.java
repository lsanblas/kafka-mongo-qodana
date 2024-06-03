package com.example.kafkamongo.services;

import com.example.kafkamongo.config.NotificationRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationRequest request){
        kafkaTemplate.send("notification", request.message());
    }
}
