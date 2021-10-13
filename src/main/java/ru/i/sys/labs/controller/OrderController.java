package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceController.OrderControllerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities")
public class OrderController {
    private final OrderControllerService orderControllerService;

    @Autowired
    public OrderController(OrderControllerService orderControllerService) {
        this.orderControllerService = orderControllerService;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderControllerService.getAllOrders();
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderControllerService.createOrder(order);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return orderControllerService.getOrderById(id);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") UUID id,
                                             @RequestBody Order orderUpdate) throws ResourceNotFoundException {
        return orderControllerService.updateOrder(id, orderUpdate);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return orderControllerService.deleteOrder(id);
    }
}
