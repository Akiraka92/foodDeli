package fooddeli.domain;

import fooddeli.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "restaurants",
    path = "restaurants"
)
public interface RestaurantRepository
    extends PagingAndSortingRepository<Restaurant, Long> {
        java.util.Optional<Restaurant> findByOrderId(Long id);
    }
