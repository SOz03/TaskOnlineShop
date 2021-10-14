package ru.i.sys.labs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    public OrderService(OrderRepositoryDAO orderRepositoryDAO) {
        this.orderRepositoryDAO = orderRepositoryDAO;
    }

    public List<Order> getAllOrders() {
        log.info("list orders");
        return orderRepositoryDAO.findAll();
    }

    public void createOrder(Order order) {
        log.info("starting product creation");
        orderRepositoryDAO.save(order);
        log.info("finished product creation");
    }

    public Order getOrderById(UUID id) throws ResourceNotFoundException {
        log.info("get order");
        return findByID(id);
    }

    public Order updateOrder(UUID id, Order orderUpdate) throws ResourceNotFoundException {
        Order order = findByID(id);
        order.setCost(orderUpdate.getCost());
        order.setDate(orderUpdate.getDate());
        order.setDelivery(orderUpdate.getDelivery());
        log.info("save order");
        orderRepositoryDAO.save(order);
        return order;
    }

    public void deleteOrder(UUID id) throws ResourceNotFoundException {
        log.info("starting delete order by id");
        findByID(id);
        orderRepositoryDAO.deleteById(id);
        log.info("finished delete order by id");
    }

    private Order findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search order");
        return orderRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("order with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о заказе с id= " + id);
                });
    }
}
