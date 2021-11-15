package ru.i.sys.labs.serviceDAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProductRepositoryDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        log.info("executing a database query 'findAll'");
        return productRepository.findAll();
    }

    public Product save(Product product) {
        log.info("executing a database query 'save'");
        return productRepository.save(product);
    }

    public Optional<Product> findById(UUID id) {
        log.info("executing a database query 'findById'");
        return productRepository.findById(id);
    }

    public void deleteById(UUID id) {
        log.info("executing a database query 'deleteById'");
        productRepository.deleteById(id);
        log.info("data received");
    }
}
