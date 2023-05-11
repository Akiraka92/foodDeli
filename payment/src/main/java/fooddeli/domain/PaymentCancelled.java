package fooddeli.domain;

import fooddeli.domain.*;
import fooddeli.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentCancelled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;

    public PaymentCancelled(Payment aggregate) {
        super(aggregate);
    }

    public PaymentCancelled() {
        super();
    }
}
