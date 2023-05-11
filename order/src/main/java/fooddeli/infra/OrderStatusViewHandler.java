package fooddeli.infra;

import fooddeli.config.kafka.KafkaProcessor;
import fooddeli.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusViewHandler {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            OrderStatus orderStatus = new OrderStatus();
            // view 객체에 이벤트의 Value 를 set 함
            orderStatus.setOrderId(orderPlaced.getOrderId());
            orderStatus.setRestaurantNo(orderPlaced.getRestaurantNo());
            orderStatus.setUserNo(orderPlaced.getUserNo());
            orderStatus.setMenu(orderPlaced.getMenu());
            orderStatus.setAmount(orderPlaced.getAmount());
            orderStatus.setStatus(orderPlaced.getStatus());
            orderStatus.setAdress(orderPlaced.getAdress());
            // view 레파지 토리에 save
            orderStatusRepository.save(orderStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCompleted_then_UPDATE_1(
        @Payload PaymentCompleted paymentCompleted
    ) {
        try {
            if (!paymentCompleted.validate()) return;
            // view 객체 조회

            List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(
                paymentCompleted.getOrderId()
            );
            for (OrderStatus orderStatus : orderStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus(paymentCompleted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderConfirmed_then_UPDATE_2(
        @Payload OrderConfirmed orderConfirmed
    ) {
        try {
            if (!orderConfirmed.validate()) return;
            // view 객체 조회

            List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(
                orderConfirmed.getOrderId()
            );
            for (OrderStatus orderStatus : orderStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus(orderConfirmed.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookStarted_then_UPDATE_3(
        @Payload CookStarted cookStarted
    ) {
        try {
            if (!cookStarted.validate()) return;
            // view 객체 조회

            List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(
                cookStarted.getOrderId()
            );
            for (OrderStatus orderStatus : orderStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus(cookStarted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenFoodReadied_then_UPDATE_4(
        @Payload FoodReadied foodReadied
    ) {
        try {
            if (!foodReadied.validate()) return;
            // view 객체 조회

            List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(
                foodReadied.getOrderId()
            );
            for (OrderStatus orderStatus : orderStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus(foodReadied.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_5(
        @Payload DeliveryStarted deliveryStarted
    ) {
        try {
            if (!deliveryStarted.validate()) return;
            // view 객체 조회

            List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(
                deliveryStarted.getOrderId()
            );
            for (OrderStatus orderStatus : orderStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus(deliveryStarted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCompleted_then_UPDATE_6(
        @Payload OrderCompleted orderCompleted
    ) {
        try {
            if (!orderCompleted.validate()) return;
            // view 객체 조회

            List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(
                orderCompleted.getOrderId()
            );
            for (OrderStatus orderStatus : orderStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus(orderCompleted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCancelled_then_UPDATE_7(
        @Payload PaymentCancelled paymentCancelled
    ) {
        try {
            if (!paymentCancelled.validate()) return;
            // view 객체 조회

            List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(
                paymentCancelled.getOrderId()
            );
            for (OrderStatus orderStatus : orderStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus(paymentCancelled.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
