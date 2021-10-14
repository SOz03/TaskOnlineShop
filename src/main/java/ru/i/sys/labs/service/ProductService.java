package ru.i.sys.labs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.ProductRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepositoryDAO productRepositoryDAO;

    @Autowired
    public ProductService(ProductRepositoryDAO productRepositoryDAO) {
        this.productRepositoryDAO = productRepositoryDAO;
    }

    public List<Product> getAllProducts() {
        return productRepositoryDAO.findAll();
    }

    public void createProduct(Product product) {
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
        productRepositoryDAO.save(product);
        return product;
    }

    public void deleteProduct(UUID id) throws ResourceNotFoundException {
        productRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о продукте с id= " + id));
        productRepositoryDAO.deleteById(id);
    }

    private Product findByID(UUID id) throws ResourceNotFoundException {
        return productRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о продукте с id= " + id));
    }

}
