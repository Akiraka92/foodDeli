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
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);


         });
        */

    }

    public static void updateOrder(OrderCompleted orderCompleted) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCompleted.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);


         });
        */

    }

    public static void updateOrder(PaymentCompleted paymentCompleted) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCompleted.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);


         });
        */

    }

    public static void cancelOrder(CancelationRequested cancelationRequested) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        OrderCancelled orderCancelled = new OrderCancelled(restaurant);
        orderCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(cancelationRequested.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);

            OrderCancelled orderCancelled = new OrderCancelled(restaurant);
            orderCancelled.publishAfterCommit();

         });
        */

    }
}
