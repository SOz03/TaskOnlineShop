package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceController.ProductControllerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop/entities")
public class ProductController {

    private final ProductControllerService productControllerService;

    @Autowired
    public ProductController(ProductControllerService productControllerService) {
        this.productControllerService = productControllerService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productControllerService.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return productControllerService.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return productControllerService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID id,
                                                 @RequestBody Product productUpdate) throws ResourceNotFoundException {
        return productControllerService.updateProduct(id, productUpdate);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return productControllerService.deleteProduct(id);
    }

}
