package ru.i.sys.labs.serviceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerBasketRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerBasketControllerService {
    private final CustomerBasketRepositoryDAO customerBasketRepositoryDAO;

    @Autowired
    public CustomerBasketControllerService(CustomerBasketRepositoryDAO customerBasketRepositoryDAO) {
        this.customerBasketRepositoryDAO = customerBasketRepositoryDAO;
    }

    public List<CustomerBasket> getAllCustomerBaskets() {
        return customerBasketRepositoryDAO.findAll();
    }

    public ResponseEntity<CustomerBasket> createCustomerBasket(CustomerBasket customerBasket) {
        customerBasketRepositoryDAO.save(customerBasket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<CustomerBasket> getCustomerBasketById(UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(findByID(id));
    }

    public ResponseEntity<CustomerBasket> updateCustomerBasket(UUID id, CustomerBasket customerBasketUpdate) throws ResourceNotFoundException {
        CustomerBasket customerBasket = findByID(id);
        customerBasket.setCustomer(customerBasketUpdate.getCustomer());
        customerBasketRepositoryDAO.save(customerBasket);
        return ResponseEntity.ok().body(customerBasket);
    }

    public ResponseEntity<CustomerBasket> deleteCustomerBasket(UUID id) throws ResourceNotFoundException {
        customerBasketRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о корзине покупателя с id= " + id));
        customerBasketRepositoryDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private CustomerBasket findByID(UUID id) throws ResourceNotFoundException {
        return customerBasketRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о корзине покупателя с id= " + id));
    }
}
