package mvp.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvp.kafka.avro.model.TransactionEvent;
import mvp.kafka.model.TransactionDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC_NAME = "demo-topic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(TransactionDTO message) {
        String jsonString = convertObjectToJson(message);
        kafkaTemplate.send(TOPIC_NAME, jsonString);
        System.out.println("Produced message: " + message);
    }

    private String convertObjectToJson(TransactionDTO object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Could not convert String into JSON");
            return "Error serializing object";
        }
    }
}
