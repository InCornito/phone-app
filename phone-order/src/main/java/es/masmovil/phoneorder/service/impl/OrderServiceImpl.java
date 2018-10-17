package es.masmovil.phoneorder.service.impl;

import es.masmovil.phoneorder.client.PhoneCatalogClient;
import es.masmovil.phoneorder.dto.OrderRequest;
import es.masmovil.phoneorder.dto.OrderResponse;
import es.masmovil.phoneorder.dto.Phone;
import es.masmovil.phoneorder.entity.Order;
import es.masmovil.phoneorder.entity.User;
import es.masmovil.phoneorder.exception.PhonesNotFoundException;
import es.masmovil.phoneorder.repository.OrderRepository;
import es.masmovil.phoneorder.service.OrderService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final PhoneCatalogClient phoneCatalogClient;

    public OrderServiceImpl(final OrderRepository orderRepository,
                            final PhoneCatalogClient phoneCatalogClient) {
        this.orderRepository = orderRepository;
        this.phoneCatalogClient = phoneCatalogClient;
    }

    @Override
    public Mono<OrderResponse> createOrder(final OrderRequest orderRequest) {
        final Mono<OrderResponse> orderResponseMono = validateOrder(orderRequest);

        return orderResponseMono.flatMap(
                orderResponse -> {
                    final String userId = orderRequest.getUserId();
                    LOGGER.info("Processing order creation. userId = '{}'.", userId);

                    final Order order = createOrder(orderRequest, orderResponse);
                    final String orderId = order.getId();

                    LOGGER.debug("Saving order with orderId = '{}'. userId = '{}'.",
                            orderId, userId);
                    orderResponse.setId(orderId);
                    final Mono<OrderResponse> responseMono =
                            orderRepository.save(order).thenReturn(orderResponse);
                    LOGGER.info("Order = '{}' was successfully created. userId = '{}'.",
                            orderId, userId);
                    System.out.println("Order was successfully created!");

                    return responseMono;
                }
        );
    }

    @Override
    public Mono<OrderResponse> validateOrder(final OrderRequest orderRequest) {
        final List<String> distinctStrings =
                orderRequest.getPhoneIds().stream().distinct().collect(Collectors.toList());
        final Flux<Phone> availablePhones = phoneCatalogClient.findByIdIn(distinctStrings);

        return availablePhones
                .switchIfEmpty(Mono.error(new PhonesNotFoundException()))
                .collectList()
                .map(phones -> processValidation(orderRequest, distinctStrings, phones));
    }

    private OrderResponse processValidation(final OrderRequest orderRequest, final List<String> distinctStrings,
                                            final List<Phone> phones) {
        LOGGER.info("Processing validation. userId = '{}'.", orderRequest.getUserId());
        final List<String> ids = phones.stream().map(Phone::getId).collect(Collectors.toList());
        LOGGER.debug("Phones received from phone-catalog: '{}'", ids.toString());
        distinctStrings.removeAll(ids); // rewrite it to pure function(without effective final side effects)

        final BigDecimal total = calculateTotal(phones);
        LOGGER.debug("createOrderResponse. userId = '{}', Total price = '{}'.",
                orderRequest.getUserId(), total);
        return createOrderResponse(ids, distinctStrings, total);
    }

    //all those private methods below can be extracted to static util class or can be made as functions
    private OrderResponse createOrderResponse(final List<String> requestedPhoneIds,
                                              final List<String> unavailablePhoneIds,
                                              final BigDecimal total) {
        final OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTotal(total);
        orderResponse.setAvailableItemIds(requestedPhoneIds);
        orderResponse.setUnavailableItemIds(unavailablePhoneIds);
        return orderResponse;
    }

    private BigDecimal calculateTotal(final List<Phone> availablePhones) {
        return availablePhones
                .stream()
                .map(Phone::getPrice)
                .reduce(BigDecimal::add)
                .orElseThrow();
    }

    private Order createOrder(final OrderRequest orderRequest, final OrderResponse orderResponse) {
        final Order order = new Order();
        order.setId(new ObjectId().toString());
        order.setUser(createUser(orderRequest));
        order.setUserIds(orderResponse.getAvailableItemIds());
        order.setTotal(orderResponse.getTotal());
        return order;
    }

    private User createUser(final OrderRequest orderRequest) {
        final User user = new User();
        user.setId(orderRequest.getUserId());
        user.setEmail(orderRequest.getUserEmail());
        user.setName(orderRequest.getUserName());
        user.setSurName(orderRequest.getuserSurname());
        return user;
    }
}
