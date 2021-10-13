package ru.i.sys.labs.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductRepositoryDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryDAO(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Optional<Product> findById(UUID id){
        return productRepository.findById(id);
    }

    public void deleteById(UUID id){
        productRepository.deleteById(id);
    }
}
