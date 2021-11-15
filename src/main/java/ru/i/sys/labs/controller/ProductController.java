package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop/entities/products")
public class ProductController {

    private final ProductService productControllerService;

    @Autowired
    public ProductController(ProductService productControllerService) {
        this.productControllerService = productControllerService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productControllerService.getAllProducts();
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productControllerService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(productControllerService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID id,
                                                 @RequestBody Product productUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(productControllerService.updateProduct(id, productUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") UUID id) {
        productControllerService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
