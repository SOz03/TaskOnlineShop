package ru.i.sys.labs.serviceDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.repository.BasketProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasketProductRepositoryDAO {

    private final Logger log = LoggerFactory.getLogger(BasketProductRepositoryDAO.class);
    private final BasketProductRepository basketProductRepository;

    @Autowired
    public BasketProductRepositoryDAO(BasketProductRepository basketProductRepository) {
        this.basketProductRepository = basketProductRepository;
    }

    public List<BasketProduct> findAll() {
        log.info("executing a database query 'findAll'");
        return basketProductRepository.findAll();
    }

    public void save(BasketProduct basketProduct) {
        log.info("executing a database query 'save'");
        basketProductRepository.save(basketProduct);
        log.info("data received");
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
