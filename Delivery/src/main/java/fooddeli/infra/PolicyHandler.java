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
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FoodReadied'"
    )
    public void wheneverFoodReadied_CreateDelivery(
        @Payload FoodReadied foodReadied
    ) {
        FoodReadied event = foodReadied;
        System.out.println(
            "\n\n##### listener CreateDelivery : " + foodReadied + "\n\n"
        );
        
        if ("FOOD_READIED".equals(foodReadied.getStatus())) {
            Delivery.createDelivery(event);
            System.out.println("##### Done\n\n");
        }
        
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCompleted'"
    )
    public void wheneverOrderCompleted_UpdateDelivery(
        @Payload OrderCompleted orderCompleted
    ) {
        OrderCompleted event = orderCompleted;
        System.out.println(
            "\n\n##### listener UpdateDelivery : " + orderCompleted + "\n\n"
        );

        if ("ORDER_COMPLETED".equals(orderCompleted.getStatus())) {
            Delivery.updateDelivery(event);
            System.out.println("##### Done\n\n");
        }
    }
}
