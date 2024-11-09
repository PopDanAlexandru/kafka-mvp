package mvp.kafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class TransactionDTO {
    @NonNull
    private String transactionMessage;
    @NonNull
    private Integer amount;
    @NonNull
    private String currency;
}
