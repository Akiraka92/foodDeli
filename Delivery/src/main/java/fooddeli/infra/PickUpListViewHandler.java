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
public class PickUpListViewHandler {

    @Autowired
    private PickUpListRepository pickUpListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenFoodReadied_then_CREATE_1(
        @Payload FoodReadied foodReadied
    ) {
        try {
            if (!foodReadied.validate()) return;

            // view 객체 생성
            PickUpList pickUpList = new PickUpList();
            // view 객체에 이벤트의 Value 를 set 함
            pickUpList.setOrderId(foodReadied.getOrderId());
            pickUpList.setRestaurantNo(foodReadied.getRestaurantNo());
            pickUpList.setUserNo(foodReadied.getUserNo());
            pickUpList.setStatus(foodReadied.getStatus());
            pickUpList.setAdress(foodReadied.getAdress());
            // view 레파지 토리에 save
            pickUpListRepository.save(pickUpList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_DELETE_1(
        @Payload DeliveryStarted deliveryStarted
    ) {
        try {
            if (!deliveryStarted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            pickUpListRepository.deleteByOrderId(deliveryStarted.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
