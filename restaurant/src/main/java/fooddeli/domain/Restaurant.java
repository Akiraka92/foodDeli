package fooddeli.domain;

import fooddeli.RestaurantApplication;
import fooddeli.domain.CookStarted;
import fooddeli.domain.FoodReadied;
import fooddeli.domain.OrderCancelled;
import fooddeli.domain.OrderConfirmed;
import fooddeli.domain.OrderDenied;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Restaurant_table")
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long restaurantNo;

    private Long userNo;

    private String menu;

    private Integer amount;

    private String status;

    private String adress;

    @PostUpdate
    public void onPostUpdate() {
        OrderConfirmed orderConfirmed = new OrderConfirmed(this);
        orderConfirmed.publishAfterCommit();

        OrderDenied orderDenied = new OrderDenied(this);
        orderDenied.publishAfterCommit();

        FoodReadied foodReadied = new FoodReadied(this);
        foodReadied.publishAfterCommit();

        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();

        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static RestaurantRepository repository() {
        RestaurantRepository restaurantRepository = RestaurantApplication.applicationContext.getBean(
            RestaurantRepository.class
        );
        return restaurantRepository;
    }

    public static void createOrder(OrderPlaced orderPlaced) {

        Restaurant restaurant = new Restaurant();
        restaurant.setOrderId(orderPlaced.getOrderId());
        restaurant.setRestaurantNo(orderPlaced.getRestaurantNo());
        restaurant.setUserNo(orderPlaced.getUserNo());
        restaurant.setMenu(orderPlaced.getMenu());
        restaurant.setAmount(orderPlaced.getAmount());
        restaurant.setAdress(orderPlaced.getAdress());
        restaurant.setStatus("ORDER_PLACED");
        repository().save(restaurant);

    }

    public static void updateOrder(OrderCompleted orderCompleted) {

        repository().findByOrderId(orderCompleted.getOrderId()).ifPresent(restaurant->{

            restaurant.setStatus("ORDER_COMPLETED");
            repository().save(restaurant);

         });

    }

    public static void updateOrder(PaymentCompleted paymentCompleted) {
        
        repository().findByOrderId(paymentCompleted.getOrderId()).ifPresent(restaurant->{
            
            restaurant.setStatus("PAYMENT_DONE");
            repository().save(restaurant);

         });
    }

    public static void cancelOrder(CancelationRequested cancelationRequested) {

        repository().findByOrderId(cancelationRequested.getOrderId()).ifPresent(restaurant->{
            
            if (restaurant.getStatus() != null){
                switch (restaurant.getStatus()) {
                    case "ORDER_PLACED" :
                    case "PAYMENT_DONE" :
                    case "ORDER_CONFIRMED" :
                        restaurant.setStatus("ORDER_CANCELLED");
                        repository().save(restaurant);

                        OrderCancelled orderCancelled = new OrderCancelled(restaurant);
                        orderCancelled.publishAfterCommit();
                }
            }
         });
    }
}
