package fooddeli.domain;

import fooddeli.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class FoodReadied extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;
    private Long restaurantNo;
    private Long userNo;
    private String adress;
}
