package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.BasketProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop/entities/basket-products")
public class BasketProductController {

    private final BasketProductService basketProductControllerService;

    @Autowired
    public BasketProductController(BasketProductService basketProductControllerService) {
        this.basketProductControllerService = basketProductControllerService;
    }

    @GetMapping("")
    public List<BasketProduct> getAllBasketProducts() {
        return basketProductControllerService.getAllBasketProducts();
    }

    @PostMapping("")
    public ResponseEntity<BasketProduct> createBasketProduct(@RequestBody BasketProduct basketProduct) {
        basketProductControllerService.createBasketProduct(basketProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketProduct> getBasketProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(basketProductControllerService.getBasketProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasketProduct> updateBasketProduct(@PathVariable(value = "id") UUID id,
                                                             @RequestBody BasketProduct basketProductUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(basketProductControllerService.updateBasketProduct(id, basketProductUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasketProduct> deleteBasketProduct(@PathVariable(value = "id") UUID id) {
        basketProductControllerService.deleteBasketProduct(id);
        return ResponseEntity.ok().build();
    }
}
