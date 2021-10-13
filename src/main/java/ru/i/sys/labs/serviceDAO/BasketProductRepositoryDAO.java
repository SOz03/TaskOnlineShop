package ru.i.sys.labs.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.repository.BasketProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasketProductRepositoryDAO {

    private final BasketProductRepository basketProductRepository;

    @Autowired
    public BasketProductRepositoryDAO(BasketProductRepository basketProductRepository) {
        this.basketProductRepository = basketProductRepository;
    }

    public List<BasketProduct> findAll() {
        return basketProductRepository.findAll();
    }

    public void save(BasketProduct basketProduct) {
        basketProductRepository.save(basketProduct);
    }

    public Optional<BasketProduct> findById(UUID id) {
        return basketProductRepository.findById(id);
    }

    public void deleteById(UUID id) {
        basketProductRepository.deleteById(id);
    }
}
