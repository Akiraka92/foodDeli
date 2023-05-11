package fooddeli.domain;

import fooddeli.domain.*;
import fooddeli.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String status;
    private Long orderId;

    public OrderCancelled(Restaurant aggregate) {
        super(aggregate);
    }

    public OrderCancelled() {
        super();
    }
}
