package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.DeliveryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop/entities/delivery")
public class DeliveryController {
    private final DeliveryService deliveryControllerService;

    @Autowired
    public DeliveryController(DeliveryService deliveryControllerService) {
        this.deliveryControllerService = deliveryControllerService;
    }

    @GetMapping("")
    public List<Delivery> getAllDelivery() {
        return deliveryControllerService.getAllDelivery();
    }

    @PostMapping("")
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        deliveryControllerService.createDelivery(delivery);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(deliveryControllerService.getDeliveryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable(value = "id") UUID id,
                                                   @RequestBody Delivery deliveryUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(deliveryControllerService.updateDelivery(id, deliveryUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Delivery> deleteDelivery(@PathVariable(value = "id") UUID id) {
        deliveryControllerService.deleteDelivery(id);
        return ResponseEntity.ok().build();
    }
}
