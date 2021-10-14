package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities/customers")
public class CustomerController {

    private final CustomerService customerControllerService;

    @Autowired
    public CustomerController(CustomerService customerControllerService) {
        this.customerControllerService = customerControllerService;
    }

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return customerControllerService.getAllCustomers();
    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return customerControllerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return customerControllerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") UUID id,
                                                   @RequestBody Customer customerUpdate) throws ResourceNotFoundException {
        return customerControllerService.updateCustomer(id, customerUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return customerControllerService.deleteCustomer(id);
    }
}
