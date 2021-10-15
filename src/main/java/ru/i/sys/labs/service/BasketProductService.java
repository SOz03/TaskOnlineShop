package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void createBasketProduct(BasketProduct basketProduct) {
        log.info("starting basket product creation");
        basketProductRepositoryDAO.save(basketProduct);
        log.info("finished basket product creation");
    }

    public BasketProduct getBasketProductById(UUID id) throws ResourceNotFoundException {
        log.info("get basket product");
        return findByID(id);
    }

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

    public void deleteBasketProduct(UUID id) throws ResourceNotFoundException {

        log.info("starting delete basket product by id");
        findByID(id);
        basketProductRepositoryDAO.deleteById(id);
        log.info("finished delete basket product by id");
    }

    private BasketProduct findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search basket product");
        return basketProductRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("basket product with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о продуктах в корзине с id = " + id);
                });
    }
}
