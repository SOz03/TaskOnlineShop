package ru.i.sys.labs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping()
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не найден" + id));
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID id,
                                                 @RequestBody Product productUpdate) throws ResourceNotFoundException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не найден" + id));
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setProductionDate(productUpdate.getProductionDate());
        product.setDescription(productUpdate.getDescription());
        productRepository.save(product);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product>  deleteProduct(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException{
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не найден" + id));
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
