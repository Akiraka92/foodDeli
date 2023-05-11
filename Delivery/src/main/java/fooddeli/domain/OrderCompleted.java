package fooddeli.domain;

import fooddeli.domain.*;
import fooddeli.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCompleted extends AbstractEvent {

    private Long orderId;
    private String status;
}
