package ru.i.sys.labs.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.repository.CustomerBasketRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerBasketRepositoryDAO {

    private final CustomerBasketRepository customerBasketRepository;

    @Autowired
    public CustomerBasketRepositoryDAO(CustomerBasketRepository customerBasketRepository) {
        this.customerBasketRepository = customerBasketRepository;
    }

    public List<CustomerBasket> findAll() {
        return customerBasketRepository.findAll();
    }

    public void save(CustomerBasket customerBasket) {
        customerBasketRepository.save(customerBasket);
    }

    public Optional<CustomerBasket> findById(UUID id) {
        return customerBasketRepository.findById(id);
    }

    public void deleteById(UUID id) {
        customerBasketRepository.deleteById(id);
    }
}
