package ru.i.sys.labs.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderRepositoryDAO {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderRepositoryDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }
}
