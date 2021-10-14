package ru.i.sys.labs.serviceDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductRepositoryDAO {

    private final Logger log = LoggerFactory.getLogger(ProductRepositoryDAO.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        log.info("executing a database query 'findAll'");
        return productRepository.findAll();
    }

    public void save(Product product) {
        log.info("executing a database query 'save'");
        productRepository.save(product);
        log.info("data received");
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
