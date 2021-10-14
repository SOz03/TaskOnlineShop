package ru.i.sys.labs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepositoryDAO customerRepositoryDAO;

    @Autowired
    public CustomerService(CustomerRepositoryDAO customerRepositoryDAO) {
        this.customerRepositoryDAO = customerRepositoryDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerRepositoryDAO.findAll();
    }

    public ResponseEntity<Customer> createCustomer(Customer customer) {
        customerRepositoryDAO.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Customer> getCustomerById(UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(findByID(id));
    }

    public ResponseEntity<Customer> updateCustomer(UUID id, Customer customerUpdate) throws ResourceNotFoundException {
        Customer customer = findByID(id);
        customer.setFIO(customerUpdate.getFIO());
        customer.setDateBirth(customerUpdate.getDateBirth());
        customer.setPhoneNumber(customerUpdate.getPhoneNumber());
        customer.setAddress(customerUpdate.getAddress());
        customerRepositoryDAO.save(customer);
        return ResponseEntity.ok().body(customer);
    }

    public ResponseEntity<Customer> deleteCustomer(UUID id) throws ResourceNotFoundException {
        customerRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о покупателе с id= " + id));
        customerRepositoryDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private Customer findByID(UUID id) throws ResourceNotFoundException {
        return customerRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о покупателе с id= " + id));
    }
}
