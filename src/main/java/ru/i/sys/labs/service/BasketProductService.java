package ru.i.sys.labs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.BasketProductRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class BasketProductService {
    private final BasketProductRepositoryDAO basketProductRepositoryDAO;

    @Autowired
    public BasketProductService(BasketProductRepositoryDAO basketProductRepositoryDAO) {
        this.basketProductRepositoryDAO = basketProductRepositoryDAO;
    }

    public List<BasketProduct> getAllBasketProducts() {
        return basketProductRepositoryDAO.findAll();
    }

    public void createBasketProduct(BasketProduct basketProduct) {
        basketProductRepositoryDAO.save(basketProduct);
    }

    public BasketProduct getBasketProductById(UUID id) throws ResourceNotFoundException {
        return findByID(id);
    }

    public BasketProduct updateBasketProduct(UUID id, BasketProduct basketProductUpdate) throws ResourceNotFoundException {
        BasketProduct basketProduct = findByID(id);
        basketProduct.setOrder(basketProductUpdate.getOrder());
        basketProduct.setCountProduct(basketProductUpdate.getCountProduct());
        basketProduct.setCustomerBasket(basketProductUpdate.getCustomerBasket());
        basketProduct.setProduct(basketProductUpdate.getProduct());
        basketProductRepositoryDAO.save(basketProduct);
        return basketProduct;
    }

    public void deleteBasketProduct(UUID id) throws ResourceNotFoundException {
        basketProductRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о продуктах в корзине с id= " + id));
        basketProductRepositoryDAO.deleteById(id);
    }

    private BasketProduct findByID(UUID id) throws ResourceNotFoundException {
        return basketProductRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о продуктах в корзине с id= " + id));
    }
}
