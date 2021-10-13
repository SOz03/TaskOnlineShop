package ru.i.sys.labs.serviceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.ProductRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class ProductControllerService {
    private final ProductRepositoryDAO productRepositoryDAO;

    @Autowired
    public ProductControllerService(ProductRepositoryDAO productRepositoryDAO) {
        this.productRepositoryDAO = productRepositoryDAO;
    }

    public List<Product> getAllProducts() {
        return productRepositoryDAO.findAll();
    }

    public ResponseEntity<Product> createProduct(Product product) {
        productRepositoryDAO.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Product> getProductById(UUID id) throws ResourceNotFoundException {
        Product product = productRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о продукте с id= " + id));
        return ResponseEntity.ok().body(product);
    }

    public ResponseEntity<Product> updateProduct(UUID id, Product productUpdate) throws ResourceNotFoundException {
        Product product = productRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о продукте с id= " + id));
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setProductionDate(productUpdate.getProductionDate());
        product.setDescription(productUpdate.getDescription());
        productRepositoryDAO.save(product);
        return ResponseEntity.ok().body(product);
    }

    public ResponseEntity<Product> deleteProduct(UUID id) throws ResourceNotFoundException {
        productRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о продукте с id= " + id));
        productRepositoryDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
