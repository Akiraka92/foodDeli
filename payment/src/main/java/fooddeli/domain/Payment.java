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

        Payment payment = new Payment();
        payment.setOrderId(orderPlaced.getOrderId());
        payment.setUserId(orderPlaced.getUserNo());
        payment.setStatus("ORDER_PLACED");
        repository().save(payment);

    }

    public static void makePayment(PaymentDone paymentDone) {

        repository().findByOrderId(paymentDone.getOrderId()).ifPresent(payment->{
            
            payment.setStatus("PAYMENT_DONE");
            repository().save(payment);

            PaymentCompleted paymentCompleted = new PaymentCompleted(payment);
            paymentCompleted.publishAfterCommit();

         });

    }

    public static void cancelPayment(OrderCancelled orderCancelled) {

        repository().findById(orderCancelled.getOrderId()).ifPresent(payment->{
            
            payment.setStatus("ORDER_CANCELLED");
            repository().save(payment);

            PaymentCancelled paymentCancelled = new PaymentCancelled(payment);
            paymentCancelled.publishAfterCommit();

         });

    }

    public static void cancelPayment(OrderDenied orderDenied) {
        
        repository().findById(orderDenied.getOrderId()).ifPresent(payment->{
            
            payment.setStatus("ORDER_DENIED");
            repository().save(payment);

            PaymentCancelled paymentCancelled = new PaymentCancelled(payment);
            paymentCancelled.publishAfterCommit();

         });

    }
}
