package ru.i.sys.labs.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void createProduct() throws ResourceNotFoundException {
        Product product = new Product("мясо", new BigDecimal("315.01"),
                new Date(), "За килограмм");

        productService.createProduct(product);
        assertNotNull(productService.getProductById(product.getId()));
    }

    @Test
    void getProductById() {

    }
}