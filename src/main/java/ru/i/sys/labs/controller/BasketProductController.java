package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.BasketProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities/basket-products")
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
        return basketProductControllerService.createBasketProduct(basketProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketProduct> getBasketProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return basketProductControllerService.getBasketProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasketProduct> updateBasketProduct(@PathVariable(value = "id") UUID id,
                                                             @RequestBody BasketProduct basketProductUpdate) throws ResourceNotFoundException {
        return basketProductControllerService.updateBasketProduct(id, basketProductUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasketProduct> deleteBasketProduct(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return basketProductControllerService.deleteBasketProduct(id);
    }
}
