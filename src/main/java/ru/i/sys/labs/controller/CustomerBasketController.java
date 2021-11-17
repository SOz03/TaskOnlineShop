package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.dto.BasketProductDTO;
import ru.i.sys.labs.dto.CustomerBasketDTO;
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
    public  ResponseEntity<List<CustomerBasketDTO>> getAllCustomerBaskets() {
        List<CustomerBasketDTO> allBasketProducts = customerBasketControllerService.getAllCustomerBaskets();
        if(allBasketProducts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(allBasketProducts);
    }

    @PostMapping("")
    public ResponseEntity<CustomerBasketDTO> createCustomerBasket(@RequestBody CustomerBasketDTO customerBasketDTO) {
        return new ResponseEntity<>(customerBasketControllerService.createCustomerBasket(customerBasketDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerBasketDTO> getCustomerBasketById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        CustomerBasketDTO customerBasketDTO = customerBasketControllerService.getCustomerBasketById(id);
        if(customerBasketDTO==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(customerBasketDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerBasketDTO> updateCustomerBasket(@PathVariable(value = "id") UUID id,
                                                               @RequestBody CustomerBasketDTO customerBasketDTOUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(customerBasketControllerService.updateCustomerBasket(id, customerBasketDTOUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerBasket> deleteCustomerBasket(@PathVariable(value = "id") UUID id) {
        customerBasketControllerService.deleteCustomerBasket(id);
        return ResponseEntity.ok().build();
    }
}
