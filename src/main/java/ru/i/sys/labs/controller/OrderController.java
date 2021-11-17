package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.dto.CustomerBasketDTO;
import ru.i.sys.labs.dto.OrderDTO;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop/entities/orders")
public class OrderController {
    private final OrderService orderControllerService;

    @Autowired
    public OrderController(OrderService orderControllerService) {
        this.orderControllerService = orderControllerService;
    }

    @GetMapping("")
    public  ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> allOrders = orderControllerService.getAllOrders();
        if(allOrders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(allOrders);
    }

    @PostMapping("")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderControllerService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        OrderDTO orderDTO = orderControllerService.getOrderById(id);
        if(orderDTO==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(orderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(value = "id") UUID id,
                                             @RequestBody OrderDTO orderUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(orderControllerService.updateOrder(id, orderUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable(value = "id") UUID id) {
        orderControllerService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
