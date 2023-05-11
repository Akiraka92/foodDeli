package fooddeli.infra;

import fooddeli.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "pickUpLists",
    path = "pickUpLists"
)
public interface PickUpListRepository
    extends PagingAndSortingRepository<PickUpList, Long> {
    void deleteByOrderId(Long orderId);
}
