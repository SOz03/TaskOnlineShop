package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.OrderService;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop/pay-order")
public class PayOrderController {

    private final OrderService orderService;

    @Autowired
    public PayOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PatchMapping("/{orderId}/{sum}")
    public ResponseEntity<Order> payOrder(@PathVariable UUID orderId,
                                          @PathVariable BigDecimal sum) throws ResourceNotFoundException {

        return new ResponseEntity<>(orderService.payOrder(orderId, sum), HttpStatus.OK);
    }
}
