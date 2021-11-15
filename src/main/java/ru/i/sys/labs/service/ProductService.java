package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.ProductRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ProductService {

    private final ProductRepositoryDAO productRepositoryDAO;

    @Autowired
    public ProductService(ProductRepositoryDAO productRepositoryDAO) {
        this.productRepositoryDAO = productRepositoryDAO;
    }

    public List<Product> getAllProducts() {
        log.info("list products");
        return productRepositoryDAO.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createProduct(Product product) {
        log.info("starting product creation");
        productRepositoryDAO.save(product);
        log.info("finished product creation");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Product getProductById(UUID id) throws ResourceNotFoundException {
        log.info("get product");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Product updateProduct(UUID id, Product productUpdate) throws ResourceNotFoundException {
        Product product = findByID(id);
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setProductionDate(productUpdate.getProductionDate());
        product.setDescription(productUpdate.getDescription());
        log.info("save product");
        productRepositoryDAO.save(product);
        return product;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProduct(UUID id) {
        log.info("starting delete product by id");
        productRepositoryDAO.deleteById(id);
        log.info("finished delete product by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    Product findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search product");
        return productRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("product with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о продукте с id = " + id);
                });
    }

}
