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
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderConfirmed'"
    )
    public void wheneverOrderConfirmed_NotifyOrderStatus(
        @Payload OrderConfirmed orderConfirmed
    ) {
        OrderConfirmed event = orderConfirmed;
        System.out.println(
            "\n\n##### listener NotifyOrderStatus : " + orderConfirmed + "\n\n"
        );

        // Sample Logic //
        Order.notifyOrderStatus(event);
        System.out.println("#####\n\n");
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_NotifyOrderStatus(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener NotifyOrderStatus : " + deliveryStarted + "\n\n"
        );

        // Sample Logic //
        Order.notifyOrderStatus(event);
        System.out.println("#####\n\n");
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CookStarted'"
    )
    public void wheneverCookStarted_NotifyOrderStatus(
        @Payload CookStarted cookStarted
    ) {
        CookStarted event = cookStarted;
        System.out.println(
            "\n\n##### listener NotifyOrderStatus : " + cookStarted + "\n\n"
        );

        // Sample Logic //
        Order.notifyOrderStatus(event);
        System.out.println("#####\n\n");
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentCancelled'"
    )
    public void wheneverPaymentCancelled_NotifyOrderStatus(
        @Payload PaymentCancelled paymentCancelled
    ) {
        PaymentCancelled event = paymentCancelled;
        System.out.println(
            "\n\n##### listener NotifyOrderStatus : " +
            paymentCancelled +
            "\n\n"
        );

        // Sample Logic //
        Order.notifyOrderStatus(event);
        System.out.println("#####\n\n");
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentCompleted'"
    )
    public void wheneverPaymentCompleted_NotifyOrderStatus(
        @Payload PaymentCompleted paymentCompleted
    ) {
        PaymentCompleted event = paymentCompleted;
        System.out.println(
            "\n\n##### listener NotifyOrderStatus : " +
            paymentCompleted +
            "\n\n"
        );

        // Sample Logic //
        Order.notifyOrderStatus(event);
        System.out.println("#####\n\n");
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_NotifyPickup(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener NotifyPickup : " + deliveryStarted + "\n\n"
        );

        // Sample Logic //
        Order.notifyPickup(event);
        System.out.println("#####\n\n");
    }
}
