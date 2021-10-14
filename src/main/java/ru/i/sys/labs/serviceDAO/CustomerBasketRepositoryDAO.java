package ru.i.sys.labs.serviceDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.repository.CustomerBasketRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerBasketRepositoryDAO {

    private final Logger log = LoggerFactory.getLogger(CustomerBasketRepositoryDAO.class);
    private final CustomerBasketRepository customerBasketRepository;

    @Autowired
    public CustomerBasketRepositoryDAO(CustomerBasketRepository customerBasketRepository) {
        this.customerBasketRepository = customerBasketRepository;
    }

    public List<CustomerBasket> findAll() {
        log.info("executing a database query 'findAll'");
        return customerBasketRepository.findAll();
    }

    public void save(CustomerBasket customerBasket) {
        log.info("executing a database query 'save'");
        customerBasketRepository.save(customerBasket);
        log.info("data received");
    }

    public Optional<CustomerBasket> findById(UUID id) {
        log.info("executing a database query 'findById'");
        return customerBasketRepository.findById(id);
    }

    public void deleteById(UUID id) {
        log.info("executing a database query 'deleteById'");
        customerBasketRepository.deleteById(id);
        log.info("data received");
    }
}
