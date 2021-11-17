package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.dto.BasketProductDTO;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.BasketProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/api/shop/entities/basket-products")
public class BasketProductController {

    private final BasketProductService basketProductControllerService;

    @Autowired
    public BasketProductController(BasketProductService basketProductControllerService) {
        this.basketProductControllerService = basketProductControllerService;
    }

    //TODO + ответ API только ResponseEntity
    @GetMapping("")
    public  ResponseEntity<List<BasketProductDTO>> getAllBasketProducts() {
        List<BasketProductDTO> allBasketProducts = basketProductControllerService.getAllBasketProducts();
        if(allBasketProducts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(allBasketProducts);
    }

    @PostMapping("")
    public ResponseEntity<BasketProductDTO> createBasketProduct(@RequestBody BasketProductDTO basketProductDTO) {
        return new ResponseEntity<>(basketProductControllerService.createBasketProduct(basketProductDTO), HttpStatus.CREATED);
    }

    //TODO + почитать про HttpStatus. нельзя отдавать 302 просто так 200 204
    @GetMapping("/{id}")
    public ResponseEntity<BasketProductDTO> getBasketProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        BasketProductDTO basketProductDTO = basketProductControllerService.getBasketProductById(id);
        if(basketProductDTO == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(basketProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasketProductDTO> updateBasketProduct(@PathVariable(value = "id") UUID id,
                                                                @RequestBody BasketProductDTO basketProductDTOUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(basketProductControllerService.updateBasketProduct(id, basketProductDTOUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasketProduct> deleteBasketProduct(@PathVariable(value = "id") UUID id) {
        basketProductControllerService.deleteBasketProduct(id);
        return ResponseEntity.ok().build();
    }
}
