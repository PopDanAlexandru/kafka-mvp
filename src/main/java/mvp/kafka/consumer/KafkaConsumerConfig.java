package mvp.kafka.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> batchFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);  // enable batch processing, because by default it consumes one message at a time
        factory.getContainerProperties().setPollTimeout(5000); // 5 seconds = how long Kafka will wait for new messages in each polling session/attempt
        factory.getContainerProperties().setIdleBetweenPolls(10000); // 10 seconds = how long the consumer waits between each polling attempt
        return factory;
    }
}
