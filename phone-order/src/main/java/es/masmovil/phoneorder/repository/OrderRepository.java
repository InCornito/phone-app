package es.masmovil.phoneorder.repository;

import es.masmovil.phoneorder.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {


}
