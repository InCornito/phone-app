package es.masmovil.phonecatalog.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.masmovil.phonecatalog.controller.request.PhoneIdsRequest;
import es.masmovil.phonecatalog.entity.Phone;
import es.masmovil.phonecatalog.service.impl.PhoneServiceImpl;
import es.masmovil.phonecatalog.util.TestPhoneUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebFluxTest
public class PhoneControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PhoneServiceImpl phoneService;

    @Test
    public void getPhones() {
        final Phone oneTestPhone = TestPhoneUtils.createOneTestPhone();
        Mockito.when(phoneService.getPhones(any())).thenReturn(Flux.just(Arrays.array(oneTestPhone)));

        webTestClient.get()
                .uri("/phones/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void getById() throws IOException {
        final Phone expectedPhone = TestPhoneUtils.createOneTestPhone();
        Mockito.when(phoneService.findById(any())).thenReturn(Mono.just(expectedPhone));

        final String result = new String(Objects.requireNonNull(
                webTestClient.get()
                        .uri("/phones/testId")
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody().returnResult().getResponseBody()));

        final Phone actualPhone = new ObjectMapper()
                .readerFor(Phone.class)
                .readValue(result);

        Assert.assertEquals(actualPhone, expectedPhone);
    }

    @Test
    public void findByIdInReturnsExactOnePhoneWhenPhoneIdsRequestIsCorrect() throws IOException {
        final Phone expectedPhone = TestPhoneUtils.createOneTestPhone();
        final PhoneIdsRequest phoneIdsRequest = TestPhoneUtils.createPhoneIdsRequest();
        Mockito.when(phoneService.findByIdIn(any())).thenReturn(Flux.just(expectedPhone));

        final List<Phone> actualPhones = getTestPhones(phoneIdsRequest);

        Assertions.assertThat(actualPhones).isNotEmpty();
        Assertions.assertThat(actualPhones).containsOnly(expectedPhone);
    }

    @Test
    public void findByIdInDoesNotReturnPhonesWhenPhoneIdsRequestIsIncorrect() {
        final PhoneIdsRequest phoneIdsRequest = new PhoneIdsRequest();

        Objects.requireNonNull(
                webTestClient.post()
                        .uri(RestPath.BASE + RestPath.BATCH_PRICES)
                        .body(BodyInserters.fromObject(phoneIdsRequest))
                        .exchange()
                        .expectStatus()
                        .isBadRequest()
                        .expectBody().isEmpty());
    }

    @Test
    public void findByIdInReturnsEmptyArrayWhenPhoneIdsRequestIsCorrectButNoPhonesFound() throws IOException {
        final PhoneIdsRequest phoneIdsRequest = TestPhoneUtils.createPhoneIdsRequest();
        Mockito.when(phoneService.findByIdIn(any())).thenReturn(Flux.empty());

        final List<Phone> actualPhones = getTestPhones(phoneIdsRequest);
        Assertions.assertThat(actualPhones).isEmpty();
    }

    private List<Phone> getTestPhones(final PhoneIdsRequest phoneIdsRequest) throws IOException {
        final String result = new String(Objects.requireNonNull(
                webTestClient.post()
                        .uri(RestPath.BASE + RestPath.BATCH_PRICES)
                        .body(BodyInserters.fromObject(phoneIdsRequest))
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody().returnResult().getResponseBody()));

        return new ObjectMapper()
                .readerFor(new TypeReference<List<Phone>>() {
                })
                .readValue(result);
    }
}