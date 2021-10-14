package ru.i.sys.labs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.ProductRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepositoryDAO productRepositoryDAO;

    @Autowired
    public ProductService(ProductRepositoryDAO productRepositoryDAO) {
        this.productRepositoryDAO = productRepositoryDAO;
    }

    public List<Product> getAllProducts() {
        log.info("list products");
        return productRepositoryDAO.findAll();
    }

    public void createProduct(Product product) {
        log.info("create product");
        productRepositoryDAO.save(product);
    }

    public Product getProductById(UUID id) throws ResourceNotFoundException {
        return findByID(id);
    }

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

    public void deleteProduct(UUID id) throws ResourceNotFoundException {
        log.info("delete product by id");
        findByID(id);
        productRepositoryDAO.deleteById(id);
    }

    private Product findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search product");
        return productRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("No product data {}", id);
                    return new ResourceNotFoundException("Нет данных о продукте с id= " + id);
                });
    }

}
