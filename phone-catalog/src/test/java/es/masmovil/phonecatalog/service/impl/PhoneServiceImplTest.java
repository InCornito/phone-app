package es.masmovil.phonecatalog.service.impl;

import es.masmovil.phonecatalog.entity.Phone;
import es.masmovil.phonecatalog.repository.PhoneRepository;
import es.masmovil.phonecatalog.util.TestPhoneUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceImplTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    @Test
    public void findByIdReturnNullWhenPhoneDoesNotExists() {
        when(phoneRepository.findPriceById(anyString())).thenReturn(Mono.empty());

        final Mono<Phone> phoneById = phoneService.findById("test");

        Assertions.assertThat(phoneById).as("not a null").isNotNull();

        Assert.assertNull(phoneById.block());
    }

    @Test
    public void findByIdReturnPhoneWhenPhoneExists() {
        final Phone expectedPhone = TestPhoneUtils.createOneTestPhone();
        when(phoneRepository.findPriceById(anyString())).thenReturn(Mono.just(expectedPhone));

        final Mono<Phone> phoneExists = phoneService.findById("test");

        Assertions.assertThat(phoneExists).as("not a null").isNotNull();

        Assert.assertEquals(phoneExists.block(), expectedPhone);
    }

    @Test
    public void getPhonesReturnEmptyListWhenNoPhonesExist() {
        when(phoneRepository.retrieveAllPhones(any())).thenReturn(Flux.empty());

        final List<Phone> phones = phoneService.getPhones(PageRequest.of(0, 5)).collectList().block();

        Assertions.assertThat(phones).as("not a null").isNotNull();

        Assert.assertEquals(phones.size(), 0);
    }

    @Test
    public void getPhonesReturnOnePhoneWhenOnePhoneExists() {
        when(phoneRepository.retrieveAllPhones(any())).thenReturn(Flux.just(TestPhoneUtils.createOneTestPhone()));

        final List<Phone> phones = phoneService.getPhones(PageRequest.of(0, 5)).collectList().block();

        Assertions.assertThat(phones).as("not a null").isNotNull();

        Assert.assertEquals(phones.size(), 1);
        Assert.assertEquals(phones.get(0), TestPhoneUtils.createOneTestPhone());
    }
}