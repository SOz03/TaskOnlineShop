package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities/orders")
public class OrderController {
    private final OrderService orderControllerService;

    @Autowired
    public OrderController(OrderService orderControllerService) {
        this.orderControllerService = orderControllerService;
    }

    @GetMapping("")
    public List<Order> getAllOrders() {
        return orderControllerService.getAllOrders();
    }

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderControllerService.createOrder(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return orderControllerService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") UUID id,
                                             @RequestBody Order orderUpdate) throws ResourceNotFoundException {
        return orderControllerService.updateOrder(id, orderUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return orderControllerService.deleteOrder(id);
    }
}
