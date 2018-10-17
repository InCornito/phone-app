package es.masmovil.phonecatalog.service;

import es.masmovil.phonecatalog.entity.Phone;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PhoneService {

    Flux<Phone> getPhones(Pageable pageable);

    Mono<Phone> findById(String id);

    Flux<Phone> findByIdIn(List<String> ids);
}
