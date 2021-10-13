package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceController.CustomerBasketControllerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities")
public class CustomerBasketController {

    private final CustomerBasketControllerService customerBasketControllerService;

    @Autowired
    public CustomerBasketController(CustomerBasketControllerService customerBasketControllerService) {
        this.customerBasketControllerService = customerBasketControllerService;
    }

    @GetMapping("/customer-baskets")
    public List<CustomerBasket> getAllCustomerBaskets() {
        return customerBasketControllerService.getAllCustomerBaskets();
    }

    @PostMapping("/customer-baskets")
    public ResponseEntity<CustomerBasket> createCustomerBasket(@RequestBody CustomerBasket customerBasket) {
        return customerBasketControllerService.createCustomerBasket(customerBasket);
    }

    @GetMapping("/customer-baskets/{id}")
    public ResponseEntity<CustomerBasket> getCustomerBasketById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return customerBasketControllerService.getCustomerBasketById(id);
    }

    @PutMapping("/customer-baskets/{id}")
    public ResponseEntity<CustomerBasket> updateCustomerBasket(@PathVariable(value = "id") UUID id,
                                                               @RequestBody CustomerBasket customerBasketUpdate) throws ResourceNotFoundException {
        return customerBasketControllerService.updateCustomerBasket(id, customerBasketUpdate);
    }

    @DeleteMapping("/customer-baskets/{id}")
    public ResponseEntity<CustomerBasket> deleteCustomerBasket(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return customerBasketControllerService.deleteCustomerBasket(id);
    }
}
