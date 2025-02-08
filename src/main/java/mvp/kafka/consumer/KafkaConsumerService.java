package mvp.kafka.consumer;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "demo-topic",
                   groupId = "demo-group",             // connect to a specific broker and topic
                   containerFactory = "batchFactory")  // configure the consumption behavior (batches, polling interval)
    public void consume(List<String> messages) {
        log.info("Consumed a batch of {} messages", messages.size());
        messages.forEach(msg -> log.info("Message: {}", msg));
    }
}
