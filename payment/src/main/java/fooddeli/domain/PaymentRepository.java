package fooddeli.domain;

import fooddeli.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "payments", path = "payments")
public interface PaymentRepository
    extends PagingAndSortingRepository<Payment, Long> {

        java.util.Optional<Payment> findByOrderId(Long id);
    }
