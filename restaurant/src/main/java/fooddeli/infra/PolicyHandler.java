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
    RestaurantRepository restaurantRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_CreateOrder(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener CreateOrder : " + orderPlaced + "\n\n"
        );
        // Sample Logic //
        Restaurant.createOrder(event);
        
        System.out.println("#####\n\n");
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCompleted'"
    )
    public void wheneverOrderCompleted_UpdateOrder(
        @Payload OrderCompleted orderCompleted
    ) {
        OrderCompleted event = orderCompleted;
        System.out.println(
            "\n\n##### listener UpdateOrder : " + orderCompleted + "\n\n"
        );

        if ("ORDER_COMPLETED".equals(orderCompleted.getStatus())){
            Restaurant.updateOrder(event);
            System.out.println("##### Done\n\n");
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentCompleted'"
    )
    public void wheneverPaymentCompleted_UpdateOrder(
        @Payload PaymentCompleted paymentCompleted
    ) {
        PaymentCompleted event = paymentCompleted;
        System.out.println(
            "\n\n##### listener UpdateOrder : " + paymentCompleted + "\n\n"
        );

        if ("PAYMENT_DONE".equals(paymentCompleted.getStatus())) {
            Restaurant.updateOrder(event);
            System.out.println("##### Done\n\n");
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CancelationRequested'"
    )
    public void wheneverCancelationRequested_CancelOrder(
        @Payload CancelationRequested cancelationRequested
    ) {
        CancelationRequested event = cancelationRequested;
        System.out.println(
            "\n\n##### listener CancelOrder : " + cancelationRequested + "\n\n"
        );

        if ("CANCEL_REQUESTED".equals(cancelationRequested.getStatus())) {
            Restaurant.cancelOrder(event);
            System.out.println("##### Done\n\n");
        }
    }
}
