package fooddeli.domain;

import fooddeli.domain.*;
import fooddeli.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentCompleted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;

    public PaymentCompleted(Payment aggregate) {
        super(aggregate);
    }

    public PaymentCompleted() {
        super();
    }
}