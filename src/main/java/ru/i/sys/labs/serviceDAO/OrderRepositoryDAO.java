package ru.i.sys.labs.serviceDAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderRepositoryDAO {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderRepositoryDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        log.info("executing a database query 'findAll'");
        return orderRepository.findAll();
    }

    public void save(Order order) {
        log.info("executing a database query 'save'");
        orderRepository.save(order);
        log.info("data received");
    }

    public Optional<Order> findById(UUID id) {
        log.info("executing a database query 'findById'");
        return orderRepository.findById(id);
    }

    public void deleteById(UUID id) {
        log.info("executing a database query 'deleteById'");
        orderRepository.deleteById(id);
        log.info("data received");
    }

    public List<Order> findListNoPay(){
        log.info("executing a database query 'findListNoPay'");
        return orderRepository.findListNoPay();
    }
}
