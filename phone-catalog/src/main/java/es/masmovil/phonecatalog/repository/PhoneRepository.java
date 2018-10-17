package es.masmovil.phonecatalog.repository;

import es.masmovil.phonecatalog.entity.Phone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PhoneRepository extends ReactiveCrudRepository<Phone, String> {

    @Query("{ id: { $exists: true }}")
    Flux<Phone> retrieveAllPhones(final Pageable page);

    @Query(fields = "{" + Phone.Meta.PRICE + " : 1}")
    Mono<Phone> findPriceById(String id);

    @Query(fields = "{" + Phone.Meta.PRICE + " : 1}")
    Flux<Phone> findPriceByIdIn(List<String> ids);
}
