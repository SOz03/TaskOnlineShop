package ru.i.sys.labs.serviceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.BasketProductRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class BasketProductControllerService {
    private final BasketProductRepositoryDAO basketProductRepositoryDAO;

    @Autowired
    public BasketProductControllerService(BasketProductRepositoryDAO basketProductRepositoryDAO) {
        this.basketProductRepositoryDAO = basketProductRepositoryDAO;
    }

    public List<BasketProduct> getAllBasketProducts() {
        return basketProductRepositoryDAO.findAll();
    }

    public ResponseEntity<BasketProduct> createBasketProduct(BasketProduct basketProduct) {
        basketProductRepositoryDAO.save(basketProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<BasketProduct> getBasketProductById(UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(findByID(id));
    }

    public ResponseEntity<BasketProduct> updateBasketProduct(UUID id, BasketProduct basketProductUpdate) throws ResourceNotFoundException {
        BasketProduct basketProduct = findByID(id);
        basketProduct.setOrder(basketProductUpdate.getOrder());
        basketProduct.setCountProduct(basketProductUpdate.getCountProduct());
        basketProduct.setCustomerBasket(basketProductUpdate.getCustomerBasket());
        basketProduct.setProduct(basketProductUpdate.getProduct());
        basketProductRepositoryDAO.save(basketProduct);
        return ResponseEntity.ok().body(basketProduct);
    }

    public ResponseEntity<BasketProduct> deleteBasketProduct(UUID id) throws ResourceNotFoundException {
        basketProductRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о продуктах в корзине с id= " + id));
        basketProductRepositoryDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private BasketProduct findByID(UUID id) throws ResourceNotFoundException {
        return basketProductRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о продуктах в корзине с id= " + id));
    }
}
