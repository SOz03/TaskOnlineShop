package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.dto.OrderDTO;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.entity.StatusPay;
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
    public ResponseEntity<OrderDTO> payOrder(@PathVariable UUID orderId,
                                             @PathVariable BigDecimal sum) throws ResourceNotFoundException {

        OrderDTO orderDTO = orderService.payOrder(orderId, sum);
        if(orderDTO.getStatus().equals(StatusPay.PAID)){
            return new ResponseEntity<>(orderDTO, HttpStatus.PAYMENT_REQUIRED);
        }
        return ResponseEntity.noContent().build();
    }
}
