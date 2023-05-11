package fooddeli.domain;

import fooddeli.DeliveryApplication;
import fooddeli.domain.DeliveryStarted;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long restaurantNo;

    private Long userNo;

    private String status;

    private String adress;

    @PostUpdate
    public void onPostUpdate() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void createDelivery(FoodReadied foodReadied) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(foodReadied.getOrderId());
        delivery.setRestaurantNo(foodReadied.getRestaurantNo());
        delivery.setUserNo(foodReadied.getUserNo());
        delivery.setAdress(foodReadied.getAdress());
        delivery.setStatus("FOOD_READIED");
        repository().save(delivery);

    }

    public static void updateDelivery(OrderCompleted orderCompleted) {

        repository().findByOrderId(orderCompleted.getOrderId()).ifPresent(delivery->{
            
            if ("DELIVERY_STARTED".equals(delivery.getStatus())) {
                delivery.setStatus("ORDER_COMPLETED");
                repository().save(delivery);
            }
         });

    }
}
