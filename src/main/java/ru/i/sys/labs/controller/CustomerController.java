package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceController.CustomerControllerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities")
public class CustomerController {

    private final CustomerControllerService customerControllerService;

    @Autowired
    public CustomerController(CustomerControllerService customerControllerService) {
        this.customerControllerService = customerControllerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerControllerService.getAllCustomers();
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return customerControllerService.createCustomer(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return customerControllerService.getCustomerById(id);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") UUID id,
                                                   @RequestBody Customer customerUpdate) throws ResourceNotFoundException {
        return customerControllerService.updateCustomer(id, customerUpdate);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return customerControllerService.deleteCustomer(id);
    }
}
