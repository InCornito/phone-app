package es.masmovil.phoneorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.masmovil.phoneorder.dto.OrderRequest;
import es.masmovil.phoneorder.dto.OrderResponse;
import es.masmovil.phoneorder.service.impl.OrderServiceImpl;
import es.masmovil.phoneorder.util.TestOrdersUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//known issues - https://github.com/spring-projects/spring-boot/issues/13627
@RunWith(SpringRunner.class)
@WebFluxTest
public class OrderControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private OrderServiceImpl orderService;

    @Test
    public void createOrderReturnsOrderResponseWhenValidOrderRequest() throws IOException {
        final OrderRequest orderRequest = TestOrdersUtils.testOrderRequest();
        final OrderResponse orderResponse = TestOrdersUtils.testOrderResponse();
        when(orderService.createOrder(any())).thenReturn(Mono.just(orderResponse));
        getOrderResponse(orderRequest, RestPath.BASE + RestPath.ROOT);
    }

    @Test
    public void validateOrderReturnsOrderResponseWhenValidOrderRequest() throws IOException {
        final OrderRequest orderRequest = TestOrdersUtils.testOrderRequest();
        final OrderResponse orderResponse = TestOrdersUtils.testOrderResponse();
        when(orderService.validateOrder(any())).thenReturn(Mono.just(orderResponse));
        getOrderResponse(orderRequest, RestPath.BASE + RestPath.ROOT + RestPath.VALIDATE);
    }

    @Test
    public void createOrderValidationTest() {
        testInvalidUserId();
        testInvalidUserName();
        testInvalidSurname();
        testEmail();
        testPhoneIds();
    }

    @Test
    public void validateOrderValidationTest() {
        testInvalidUserId(); //there is no reason to repeat all createOrderValidationTest as we use the same object
    }

    private void testPhoneIds() {
        OrderRequest orderRequest = TestOrdersUtils.testOrderRequest();
        orderRequest.setPhoneIds(null);
        expectBadRequest(orderRequest);
        orderRequest.setPhoneIds(List.of());
        expectBadRequest(orderRequest);
    }

    private void testEmail() {
        OrderRequest orderRequest = TestOrdersUtils.testOrderRequest();
        orderRequest.setUserEmail("");
        expectBadRequest(orderRequest);
        orderRequest.setUserEmail(null);
        expectBadRequest(orderRequest);
        orderRequest.setUserEmail("asdasd"); //malformed email
        expectBadRequest(orderRequest);
    }

    private void testInvalidSurname() {
        OrderRequest orderRequest = TestOrdersUtils.testOrderRequest();
        orderRequest.setuserSurname("");
        expectBadRequest(orderRequest);
        orderRequest.setuserSurname(null);
        expectBadRequest(orderRequest);
    }

    private void testInvalidUserName() {
        OrderRequest orderRequest = TestOrdersUtils.testOrderRequest();
        orderRequest.setUserName("");
        expectBadRequest(orderRequest);
        orderRequest.setUserName(null);
        expectBadRequest(orderRequest);
    }

    private void testInvalidUserId() {
        OrderRequest orderRequest = TestOrdersUtils.testOrderRequest();
        orderRequest.setUserId("");
        expectBadRequest(orderRequest);
        orderRequest.setUserId(null);
        expectBadRequest(orderRequest);
    }

    private void expectBadRequest(final OrderRequest orderRequest) {
        webTestClient.post()
                .uri(RestPath.BASE + RestPath.ROOT)
                .body(BodyInserters.fromObject(orderRequest))
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody().isEmpty();
    }

    private OrderResponse getOrderResponse(final OrderRequest orderRequest, final String uri) throws IOException {
        final String result = new String(Objects.requireNonNull(webTestClient.post()
                .uri(uri)
                .body(BodyInserters.fromObject(orderRequest))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().returnResult().getResponseBody()));

        return new ObjectMapper()
                .readerFor(OrderResponse.class)
                .readValue(result);
    }
}