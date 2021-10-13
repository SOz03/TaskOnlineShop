package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceController.BasketProductControllerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities")
public class BasketProductController {

    private final BasketProductControllerService basketProductControllerService;

    @Autowired
    public BasketProductController(BasketProductControllerService basketProductControllerService) {
        this.basketProductControllerService = basketProductControllerService;
    }

    @GetMapping("/basket-products")
    public List<BasketProduct> getAllBasketProducts() {
        return basketProductControllerService.getAllBasketProducts();
    }

    @PostMapping("/basket-products")
    public ResponseEntity<BasketProduct> createBasketProduct(@RequestBody BasketProduct basketProduct) {
        return basketProductControllerService.createBasketProduct(basketProduct);
    }

    @GetMapping("/basket-products/{id}")
    public ResponseEntity<BasketProduct> getBasketProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return basketProductControllerService.getBasketProductById(id);
    }

    @PutMapping("/basket-products/{id}")
    public ResponseEntity<BasketProduct> updateBasketProduct(@PathVariable(value = "id") UUID id,
                                                             @RequestBody BasketProduct basketProductUpdate) throws ResourceNotFoundException {
        return basketProductControllerService.updateBasketProduct(id, basketProductUpdate);
    }

    @DeleteMapping("/basket-products/{id}")
    public ResponseEntity<BasketProduct> deleteBasketProduct(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return basketProductControllerService.deleteBasketProduct(id);
    }
}
