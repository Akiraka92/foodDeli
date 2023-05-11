package fooddeli.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PickUpList_table")
@Data
public class PickUpList {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long orderId;
    private Long restaurantNo;
    private Long userNo;
    private String status;
    private String adress;
}
