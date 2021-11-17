package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.dto.CustomerBasketDTO;
import ru.i.sys.labs.dto.CustomerDTO;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop/entities/customers")
public class CustomerController {

    private final CustomerService customerControllerService;

    @Autowired
    public CustomerController(CustomerService customerControllerService) {
        this.customerControllerService = customerControllerService;
    }

    @GetMapping("")
    public  ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> allBasketProducts = customerControllerService.getAllCustomers();
        if(allBasketProducts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(allBasketProducts);
    }

    @PostMapping("")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerControllerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        CustomerDTO customerDTO = customerControllerService.getCustomerById(id);
        if(customerDTO==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(customerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable(value = "id") UUID id,
                                                   @RequestBody CustomerDTO customerDTOUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(customerControllerService.updateCustomer(id, customerDTOUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") UUID id) {
        customerControllerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
