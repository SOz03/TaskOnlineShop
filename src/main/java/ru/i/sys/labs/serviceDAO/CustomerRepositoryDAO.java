package ru.i.sys.labs.serviceDAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CustomerRepositoryDAO {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        log.info("executing a database query 'findAll'");
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        log.info("executing a database query 'save'");
        customerRepository.save(customer);
        log.info("data received");
    }

    public Optional<Customer> findById(UUID id) {
        log.info("executing a database query 'findById'");
        return customerRepository.findById(id);
    }

    public void deleteById(UUID id) {
        log.info("executing a database query 'deleteById'");
        customerRepository.deleteById(id);
        log.info("data received");
    }
}
