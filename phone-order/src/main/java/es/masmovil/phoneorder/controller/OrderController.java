package es.masmovil.phoneorder.controller;

import es.masmovil.phoneorder.dto.OrderRequest;
import es.masmovil.phoneorder.dto.OrderResponse;
import es.masmovil.phoneorder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(RestPath.BASE)
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<OrderResponse> createOrder(@RequestBody @Valid final OrderRequest orderRequest) {
        LOGGER.info("Inside createOrder. userId = '{}'.", orderRequest.getUserId());
        return orderService.createOrder(orderRequest);

    }

    @PostMapping(RestPath.VALIDATE)
    public Mono<OrderResponse> validateOrder(@RequestBody @Valid final OrderRequest orderRequest) {
        LOGGER.info("Inside validateOrder. userId = '{}'.", orderRequest.getUserId());
        return orderService.validateOrder(orderRequest);
    }

}
