package ru.i.sys.labs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    public OrderService(OrderRepositoryDAO orderRepositoryDAO) {
        this.orderRepositoryDAO = orderRepositoryDAO;
    }

    public List<Order> getAllOrders() {
        return orderRepositoryDAO.findAll();
    }

    public void createOrder(Order order) {
        orderRepositoryDAO.save(order);
    }

    public Order getOrderById(UUID id) throws ResourceNotFoundException {
        return findByID(id);
    }

    public Order updateOrder(UUID id, Order orderUpdate) throws ResourceNotFoundException {
        Order order = findByID(id);
        order.setCost(orderUpdate.getCost());
        order.setDate(orderUpdate.getDate());
        order.setDelivery(orderUpdate.getDelivery());
        orderRepositoryDAO.save(order);
        return order;
    }

    public void deleteOrder(UUID id) throws ResourceNotFoundException {
        orderRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о заказе с id= " + id));
        orderRepositoryDAO.deleteById(id);
    }

    private Order findByID(UUID id) throws ResourceNotFoundException {
        return orderRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о заказе с id= " + id));
    }
}
