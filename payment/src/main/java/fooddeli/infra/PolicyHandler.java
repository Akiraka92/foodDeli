package fooddeli.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeli.config.kafka.KafkaProcessor;
import fooddeli.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_CreatePaymentInfo(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener CreatePaymentInfo : " + orderPlaced + "\n\n"
        );
        // Sample Logic //
        Payment.createPaymentInfo(event);
        
        System.out.println("#####\n\n");
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentDone'"
    )
    public void wheneverPaymentDone_MakePayment(
        @Payload PaymentDone paymentDone
    ) {
        PaymentDone event = paymentDone;
        System.out.println(
            "\n\n##### listener MakePayment : " + paymentDone + "\n\n"
        );

        if ("PAYMENT_DONE".equals(paymentDone.getStatus())) {
            Payment.makePayment(event);
            System.out.println("##### Done\n\n");
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCancelled'"
    )
    public void wheneverOrderCancelled_CancelPayment(
        @Payload OrderCancelled orderCancelled
    ) {
        OrderCancelled event = orderCancelled;
        System.out.println(
            "\n\n##### listener CancelPayment : " + orderCancelled + "\n\n"
        );

        if ("ORDER_CANCELLED".equals(orderCancelled.getStatus())) {
            Payment.cancelPayment(event);
            System.out.println("##### Done\n\n");
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderDenied'"
    )
    public void wheneverOrderDenied_CancelPayment(
        @Payload OrderDenied orderDenied
    ) {
        OrderDenied event = orderDenied;
        System.out.println(
            "\n\n##### listener CancelPayment : " + orderDenied + "\n\n"
        );

        if ("ORDER_DENIED".equals(orderDenied.getStatus())) {
            Payment.cancelPayment(event);
            System.out.println("##### Done\n\n");
        }
    }
}
