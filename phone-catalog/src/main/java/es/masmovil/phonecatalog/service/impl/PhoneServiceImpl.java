package es.masmovil.phonecatalog.service.impl;

import es.masmovil.phonecatalog.entity.Phone;
import es.masmovil.phonecatalog.repository.PhoneRepository;
import es.masmovil.phonecatalog.service.PhoneService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(final PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Flux<Phone> getPhones(final Pageable pageable) {
        //it would be better to add mapper and response with PhoneDto instead of Phone entity
        return phoneRepository.retrieveAllPhones(pageable);
    }

    @Override
    public Mono<Phone> findById(final String id) {
        return phoneRepository.findPriceById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Flux<Phone> findByIdIn(final List<String> ids) {
        return phoneRepository.findPriceByIdIn(ids.stream().distinct().collect(Collectors.toList()));
    }
}
