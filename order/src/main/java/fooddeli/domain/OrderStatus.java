package fooddeli.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderStatus_table")
@Data
public class OrderStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long orderId;
    private Long restaurantNo;
    private Long userNo;
    private String menu;
    private Integer amount;
    private String status;
    private String adress;
}
