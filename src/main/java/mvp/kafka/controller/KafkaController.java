package mvp.kafka.controller;

import mvp.kafka.model.TransactionDTO;
import mvp.kafka.producer.KafkaProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/produce")
    public ResponseEntity<String> produceMessage(@RequestBody TransactionDTO transactionDTO) {
        producerService.sendMessage(createTransactionEvent(transactionDTO.getTransactionMessage(),
                                                           transactionDTO.getAmount(),
                                                           transactionDTO.getCurrency()));
        return new ResponseEntity<>(String.format("Message successfully sent to Kafka: transactionMessage='%s' amount=%s currency=%s",
                             transactionDTO.getTransactionMessage(),
                             transactionDTO.getAmount(),
                             transactionDTO.getCurrency()), HttpStatus.OK);
    }

    private TransactionDTO createTransactionEvent(String transactionMessage, Integer amount, String currency) {
        return TransactionDTO.builder()
                .transactionMessage(transactionMessage)
                .amount(amount)
                .currency(currency)
                .build();
    }
}
