package fooddeli.domain;

import fooddeli.OrderApplication;
import fooddeli.domain.CancelationRequested;
import fooddeli.domain.OrderCompleted;
import fooddeli.domain.OrderPlaced;
import fooddeli.domain.PaymentDone;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Long restaurantNo;

    private Long userNo;

    private String menu;

    private Integer amount;

    private String status;

    private String adress;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        CancelationRequested cancelationRequested = new CancelationRequested(
            this
        );
        cancelationRequested.publishAfterCommit();

        OrderCompleted orderCompleted = new OrderCompleted(this);
        orderCompleted.publishAfterCommit();

        PaymentDone paymentDone = new PaymentDone(this);
        paymentDone.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public static void notifyOrderStatus(OrderConfirmed orderConfirmed) {
        
        repository().findById(orderConfirmed.getOrderId()).ifPresent(order->{
            
            order.setStatus(orderConfirmed.getStatus());
            repository().save(order);


         });

    }

    public static void notifyOrderStatus(DeliveryStarted deliveryStarted) {

        repository().findById(deliveryStarted.getOrderId()).ifPresent(order->{
    
            order.setStatus(deliveryStarted.getStatus());
            repository().save(order);

         });

    }

    public static void notifyOrderStatus(CookStarted cookStarted) {

        repository().findById(cookStarted.getOrderId()).ifPresent(order->{
            
            order.setStatus(cookStarted.getStatus());
            repository().save(order);


         });

    }

    public static void notifyOrderStatus(PaymentCancelled paymentCancelled) {

        repository().findById(paymentCancelled.getOrderId()).ifPresent(order->{
            
            order.setStatus(paymentCancelled.getStatus());
            repository().save(order);


         });

    }

    public static void notifyOrderStatus(PaymentCompleted paymentCompleted) {

        repository().findById(paymentCompleted.getOrderId()).ifPresent(order->{
            
            order.setStatus(paymentCompleted.getStatus());
            repository().save(order);


         });

    }

    public static void notifyPickup(DeliveryStarted deliveryStarted) {

        System.out.println("\n\n\nDELIVERY START!\n\n\n");

    }
}
