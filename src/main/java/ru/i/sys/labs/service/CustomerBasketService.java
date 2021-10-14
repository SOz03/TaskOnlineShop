package ru.i.sys.labs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerBasketRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerBasketService {
    private final CustomerBasketRepositoryDAO customerBasketRepositoryDAO;

    @Autowired
    public CustomerBasketService(CustomerBasketRepositoryDAO customerBasketRepositoryDAO) {
        this.customerBasketRepositoryDAO = customerBasketRepositoryDAO;
    }

    public List<CustomerBasket> getAllCustomerBaskets() {
        return customerBasketRepositoryDAO.findAll();
    }

    public void createCustomerBasket(CustomerBasket customerBasket) {
        customerBasketRepositoryDAO.save(customerBasket);
    }

    public CustomerBasket getCustomerBasketById(UUID id) throws ResourceNotFoundException {
        return findByID(id);
    }

    public CustomerBasket updateCustomerBasket(UUID id, CustomerBasket customerBasketUpdate) throws ResourceNotFoundException {
        CustomerBasket customerBasket = findByID(id);
        customerBasket.setCustomer(customerBasketUpdate.getCustomer());
        customerBasketRepositoryDAO.save(customerBasket);
        return customerBasket;
    }

    public void deleteCustomerBasket(UUID id) throws ResourceNotFoundException {
        customerBasketRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о корзине покупателя с id= " + id));
        customerBasketRepositoryDAO.deleteById(id);
    }

    private CustomerBasket findByID(UUID id) throws ResourceNotFoundException {
        return customerBasketRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о корзине покупателя с id= " + id));
    }
}
