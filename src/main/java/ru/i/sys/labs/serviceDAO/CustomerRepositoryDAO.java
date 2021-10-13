package ru.i.sys.labs.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerRepositoryDAO {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }

    public void deleteById(UUID id) {
        customerRepository.deleteById(id);
    }
}
