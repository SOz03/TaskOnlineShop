package ru.i.sys.labs.serviceDAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.dto.CustomerDTO;
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

    public List<Customer> findAll(Specification<Customer> specification){
        log.info("executing a database query 'findAll' and specification {}", specification.getClass());
        return customerRepository.findAll(specification);
    }

    public Customer save(Customer customer) {
        log.info("executing a database query 'save'");
        return customerRepository.save(customer);
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

    public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
        log.info("executing a database query 'getCustomerByPhoneNumber'");
        return customerRepository.getCustomerByPhoneNumber(phoneNumber);
    }
}
