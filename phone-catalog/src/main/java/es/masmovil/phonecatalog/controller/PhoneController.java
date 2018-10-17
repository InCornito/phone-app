package es.masmovil.phonecatalog.controller;

import es.masmovil.phonecatalog.controller.request.PhoneIdsRequest;
import es.masmovil.phonecatalog.entity.Phone;
import es.masmovil.phonecatalog.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(RestPath.BASE)
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(final PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping(RestPath.ROOT)
    public Flux<Phone> getPhones(final @RequestParam(defaultValue = "0") int page,
                                 final @RequestParam(defaultValue = "5") int size) {
        return phoneService.getPhones(PageRequest.of(page, size));
    }

    @GetMapping(RestPath.ID)
    public Mono<Phone> findById(@PathVariable final String id) {
         return phoneService.findById(id);
    }

    @PostMapping(RestPath.BATCH_PRICES)
    public Flux<Phone> findByIdIn(@RequestBody @Valid final PhoneIdsRequest phoneIdsRequest) {
        return phoneService.findByIdIn(phoneIdsRequest.getIds());
    }
}
