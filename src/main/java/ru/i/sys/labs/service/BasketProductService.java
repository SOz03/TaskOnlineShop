package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.BasketProductRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BasketProductService {

    private final BasketProductRepositoryDAO basketProductRepositoryDAO;

    @Autowired
    public BasketProductService(BasketProductRepositoryDAO basketProductRepositoryDAO) {
        this.basketProductRepositoryDAO = basketProductRepositoryDAO;
    }

    public List<BasketProduct> getAllBasketProducts() {
        log.info("list basket products");
        return basketProductRepositoryDAO.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createBasketProduct(BasketProduct basketProduct) {
        log.info("starting basket product creation");
        basketProductRepositoryDAO.save(basketProduct);
        log.info("finished basket product creation");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BasketProduct getBasketProductById(UUID id) throws ResourceNotFoundException {
        log.info("get basket product");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BasketProduct updateBasketProduct(UUID id, BasketProduct basketProductUpdate) throws ResourceNotFoundException {
        BasketProduct basketProduct = findByID(id);
        basketProduct.setOrder(basketProductUpdate.getOrder());
        basketProduct.setCountProduct(basketProductUpdate.getCountProduct());
        basketProduct.setCustomerBasket(basketProductUpdate.getCustomerBasket());
        basketProduct.setProduct(basketProductUpdate.getProduct());
        log.info("save basket product");
        basketProductRepositoryDAO.save(basketProduct);
        return basketProduct;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBasketProduct(UUID id) {
        log.info("starting delete basket product by id");
        basketProductRepositoryDAO.deleteById(id);
        log.info("finished delete basket product by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    BasketProduct findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search basket product");
        return basketProductRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("basket product with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о продуктах в корзине с id = " + id);
                });
    }
}
