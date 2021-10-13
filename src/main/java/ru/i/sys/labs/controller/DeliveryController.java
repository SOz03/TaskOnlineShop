package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceController.DeliveryControllerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities")
public class DeliveryController {
    private final DeliveryControllerService deliveryControllerService;

    @Autowired
    public DeliveryController(DeliveryControllerService deliveryControllerService) {
        this.deliveryControllerService = deliveryControllerService;
    }

    @GetMapping("/delivery")
    public List<Delivery> getAllDelivery() {
        return deliveryControllerService.getAllDelivery();
    }

    @PostMapping("/delivery")
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return deliveryControllerService.createDelivery(delivery);
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return deliveryControllerService.getDeliveryById(id);
    }

    @PutMapping("/delivery/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable(value = "id") UUID id,
                                                   @RequestBody Delivery deliveryUpdate) throws ResourceNotFoundException {
        return deliveryControllerService.updateDelivery(id, deliveryUpdate);
    }

    @DeleteMapping("/delivery/{id}")
    public ResponseEntity<Delivery> deleteDelivery(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return deliveryControllerService.deleteDelivery(id);
    }
}
