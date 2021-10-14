package ru.i.sys.labs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerBasketRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerBasketService {

    private final Logger log = LoggerFactory.getLogger(CustomerBasketService.class);
    private final CustomerBasketRepositoryDAO customerBasketRepositoryDAO;

    @Autowired
    public CustomerBasketService(CustomerBasketRepositoryDAO customerBasketRepositoryDAO) {
        this.customerBasketRepositoryDAO = customerBasketRepositoryDAO;
    }

    public List<CustomerBasket> getAllCustomerBaskets() {
        log.info("list customer basket");
        return customerBasketRepositoryDAO.findAll();
    }

    public void createCustomerBasket(CustomerBasket customerBasket) {
        log.info("starting customer basket creation");
        customerBasketRepositoryDAO.save(customerBasket);
        log.info("finished customer basket creation");
    }

    public CustomerBasket getCustomerBasketById(UUID id) throws ResourceNotFoundException {
        log.info("get customer basket");
        return findByID(id);
    }

    public CustomerBasket updateCustomerBasket(UUID id, CustomerBasket customerBasketUpdate) throws ResourceNotFoundException {
        CustomerBasket customerBasket = findByID(id);
        customerBasket.setCustomer(customerBasketUpdate.getCustomer());
        log.info("save customer basket");
        customerBasketRepositoryDAO.save(customerBasket);
        return customerBasket;
    }

    public void deleteCustomerBasket(UUID id) throws ResourceNotFoundException {
        log.info("starting delete customer basket by id");
        findByID(id);
        customerBasketRepositoryDAO.deleteById(id);
        log.info("finished delete customer basket by id");
    }

    private CustomerBasket findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search customer basket");
        return customerBasketRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("customer basket with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о корзине покупателя с id = " + id);
                });
    }
}
