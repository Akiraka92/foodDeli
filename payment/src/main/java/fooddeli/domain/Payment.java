package fooddeli.domain;

import fooddeli.PaymentApplication;
import fooddeli.domain.PaymentCancelled;
import fooddeli.domain.PaymentCompleted;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long userId;

    private String status;

    @PostUpdate
    public void onPostUpdate() {
        PaymentCompleted paymentCompleted = new PaymentCompleted(this);
        paymentCompleted.publishAfterCommit();

        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
        paymentCancelled.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void createPaymentInfo(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

    }

    public static void makePayment(PaymentDone paymentDone) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        PaymentCompleted paymentCompleted = new PaymentCompleted(payment);
        paymentCompleted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentDone.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);

            PaymentCompleted paymentCompleted = new PaymentCompleted(payment);
            paymentCompleted.publishAfterCommit();

         });
        */

    }

    public static void cancelPayment(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        PaymentCancelled paymentCancelled = new PaymentCancelled(payment);
        paymentCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);

            PaymentCancelled paymentCancelled = new PaymentCancelled(payment);
            paymentCancelled.publishAfterCommit();

         });
        */

    }

    public static void cancelPayment(OrderDenied orderDenied) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        PaymentCancelled paymentCancelled = new PaymentCancelled(payment);
        paymentCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderDenied.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);

            PaymentCancelled paymentCancelled = new PaymentCancelled(payment);
            paymentCancelled.publishAfterCommit();

         });
        */

    }
}
