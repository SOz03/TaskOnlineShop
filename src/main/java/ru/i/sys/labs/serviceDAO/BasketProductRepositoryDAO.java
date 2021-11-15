package ru.i.sys.labs.serviceDAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.repository.BasketProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BasketProductRepositoryDAO {

    private final BasketProductRepository basketProductRepository;

    @Autowired
    public BasketProductRepositoryDAO(BasketProductRepository basketProductRepository) {
        this.basketProductRepository = basketProductRepository;
    }

    public List<BasketProduct> findAll() {
        log.info("executing a database query 'findAll'");
        return basketProductRepository.findAll();
    }

    public BasketProduct save(BasketProduct basketProduct) {
        log.info("executing a database query 'save'");
        return basketProductRepository.save(basketProduct);
    }

    public Optional<BasketProduct> findById(UUID id) {
        log.info("executing a database query 'findById'");
        return basketProductRepository.findById(id);
    }

    public void deleteById(UUID id) {
        log.info("executing a database query 'deleteById'");
        basketProductRepository.deleteById(id);
        log.info("data received");
    }
}
