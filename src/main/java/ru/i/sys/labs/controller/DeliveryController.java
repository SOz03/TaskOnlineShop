package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.dto.DeliveryDTO;
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
    public List<DeliveryDTO> getAllDelivery() {
        return deliveryControllerService.getAllDelivery();
    }

    @PostMapping("")
    public ResponseEntity<DeliveryDTO> createDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        return new ResponseEntity<>(deliveryControllerService.createDelivery(deliveryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDTO> getDeliveryById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return new ResponseEntity<>(deliveryControllerService.getDeliveryById(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryDTO> updateDelivery(@PathVariable(value = "id") UUID id,
                                                      @RequestBody DeliveryDTO deliveryUpdate) throws ResourceNotFoundException {
        return new ResponseEntity<>(deliveryControllerService.updateDelivery(id, deliveryUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Delivery> deleteDelivery(@PathVariable(value = "id") UUID id) {
        deliveryControllerService.deleteDelivery(id);
        return ResponseEntity.ok().build();
    }
}
