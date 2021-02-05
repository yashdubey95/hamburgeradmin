package com.example.hamburgeradmin.interceptor;

import com.example.hamburgeradmin.model.APIDetails;
import com.example.hamburgeradmin.repository.APIDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.example.hamburgeradmin.util.Constants.TOPIC;
import static com.example.hamburgeradmin.util.Constants.GROUP_ID_CONFIG;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private final APIDetailsRepository apiDetailsRepository;

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID_CONFIG)
    public void consumeJson(APIDetails apiDetails) {
        System.out.println("Consumed JSON Message: " + apiDetails);
        apiDetailsRepository.save(apiDetails);
    }
}
