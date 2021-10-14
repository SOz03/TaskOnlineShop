package ru.i.sys.labs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepositoryDAO customerRepositoryDAO;

    @Autowired
    public CustomerService(CustomerRepositoryDAO customerRepositoryDAO) {
        this.customerRepositoryDAO = customerRepositoryDAO;
    }

    public List<Customer> getAllCustomers() {
        log.info("list customers");
        return customerRepositoryDAO.findAll();
    }

    public void createCustomer(Customer customer) {
        log.info("starting customer creation");
        customerRepositoryDAO.save(customer);
        log.info("finished customer creation");
    }

    public Customer getCustomerById(UUID id) throws ResourceNotFoundException {
        log.info("get customer");
        return findByID(id);
    }

    public Customer updateCustomer(UUID id, Customer customerUpdate) throws ResourceNotFoundException {
        Customer customer = findByID(id);
        customer.setFIO(customerUpdate.getFIO());
        customer.setDateBirth(customerUpdate.getDateBirth());
        customer.setPhoneNumber(customerUpdate.getPhoneNumber());
        customer.setAddress(customerUpdate.getAddress());
        log.info("save customer");
        customerRepositoryDAO.save(customer);
        return customer;
    }

    public void deleteCustomer(UUID id) throws ResourceNotFoundException {
        log.info("starting delete customer by id");
        findByID(id);
        customerRepositoryDAO.deleteById(id);
        log.info("finished delete customer by id");
    }

    private Customer findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search customer");
        return customerRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("customer with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о покупателе с id = " + id);
                });
    }
}
