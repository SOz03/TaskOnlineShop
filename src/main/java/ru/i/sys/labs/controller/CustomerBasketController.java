package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.CustomerBasketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop/entities/customer-baskets")
public class CustomerBasketController {

    private final CustomerBasketService customerBasketControllerService;

    @Autowired
    public CustomerBasketController(CustomerBasketService customerBasketControllerService) {
        this.customerBasketControllerService = customerBasketControllerService;
    }

    @GetMapping("")
    public List<CustomerBasket> getAllCustomerBaskets() {
        return customerBasketControllerService.getAllCustomerBaskets();
    }

    @PostMapping("")
    public ResponseEntity<CustomerBasket> createCustomerBasket(@RequestBody CustomerBasket customerBasket) {
        customerBasketControllerService.createCustomerBasket(customerBasket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerBasket> getCustomerBasketById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(customerBasketControllerService.getCustomerBasketById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerBasket> updateCustomerBasket(@PathVariable(value = "id") UUID id,
                                                               @RequestBody CustomerBasket customerBasketUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(customerBasketControllerService.updateCustomerBasket(id, customerBasketUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerBasket> deleteCustomerBasket(@PathVariable(value = "id") UUID id) {
        customerBasketControllerService.deleteCustomerBasket(id);
        return ResponseEntity.ok().build();
    }
}
