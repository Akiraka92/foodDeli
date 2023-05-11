package fooddeli.domain;

import fooddeli.domain.*;
import fooddeli.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderConfirmed extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;

    public OrderConfirmed(Restaurant aggregate) {
        super(aggregate);
    }

    public OrderConfirmed() {
        super();
    }
}
