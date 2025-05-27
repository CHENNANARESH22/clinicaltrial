package com.pfizer.clinicaltrial.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "clinical-trial-topic", groupId = "clinicaltrial-group")
    public void listen(String message) {
        System.out.println("🟢 Kafka message received: " + message);
        // 🔄 Here you can log it, store it in DB, or trigger another process
    }
}
