package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.dto.ProductDTO;
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
    public List<ProductDTO> getAllProducts() {
        return productControllerService.getAllProducts();
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productControllerService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return new ResponseEntity<>(productControllerService.getProductById(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable(value = "id") UUID id,
                                                 @RequestBody ProductDTO productUpdate) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(productControllerService.updateProduct(id, productUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") UUID id) {
        productControllerService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
