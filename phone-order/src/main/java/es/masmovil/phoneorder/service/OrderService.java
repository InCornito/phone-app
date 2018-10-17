package es.masmovil.phoneorder.service;

import es.masmovil.phoneorder.dto.OrderRequest;
import es.masmovil.phoneorder.dto.OrderResponse;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OrderResponse> createOrder(OrderRequest orderRequest);

    Mono<OrderResponse> validateOrder(OrderRequest orderRequest);
}
