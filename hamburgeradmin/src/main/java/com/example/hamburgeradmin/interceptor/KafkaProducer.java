package com.example.hamburgeradmin.interceptor;

import com.example.hamburgeradmin.model.APIDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

import static com.example.hamburgeradmin.util.Constants.TOPIC;
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, APIDetails> kafkaTemplate;

    public void producer(HttpServletRequest request, LocalDate timeStamp, long time) {
        APIDetails apiDetails = new APIDetails();
        apiDetails.setReqName(request.getMethod());
        apiDetails.setReqUrl(request.getRequestURL().toString());
        apiDetails.setReqTimeStamp(timeStamp);
        apiDetails.setReqExecTime(time);
        kafkaTemplate.send(TOPIC, apiDetails);
    }
}
