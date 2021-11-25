package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.dto.CustomerDTO;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerRepositoryDAO;
import ru.i.sys.labs.specification.CustomerSpecification;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepositoryDAO customerRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepositoryDAO customerRepositoryDAO, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.customerRepo = customerRepositoryDAO;
    }

    public List<Customer> findBirthday() {
        Date today = new Date();
        Specification<Customer> specification = Specification.where(CustomerSpecification.customerHasBirthday(today));

        return customerRepo.findAll(specification);
    }

    public List<CustomerDTO> getAllCustomers() {
        log.info("list customers");
        return customerRepo
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        log.info("starting customer creation");
        return toDTO(customerRepo.save(toEntity(customerDTO)));
    }

    public CustomerDTO getCustomerById(UUID id) throws ResourceNotFoundException {
        log.info("get customer");
        return findByID(id);
    }

    public CustomerDTO getCustomerByPhoneNumber(String phoneNumber) throws ResourceNotFoundException {
        log.info("get customer");
        Customer customer = customerRepo.getCustomerByPhoneNumber(phoneNumber).orElseThrow(() -> {
            log.warn("customer with prone {} not found", phoneNumber);

            return new ResourceNotFoundException("Нет данных о покупателе с номером телефона  " + phoneNumber);
        });

        return toDTO(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO updateCustomer(UUID id, CustomerDTO customerDTOUpdate) throws ResourceNotFoundException {
        Customer customer = customerRepo
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("customer with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о покупателе с id = " + id);
                });
        customer.setFIO(customerDTOUpdate.getFIO());
        customer.setDateBirth(customerDTOUpdate.getDateBirth());
        customer.setPhoneNumber(customerDTOUpdate.getPhoneNumber());
        customer.setAddress(customerDTOUpdate.getAddress());

        log.info("save customer");

        return toDTO(customerRepo.save(customer));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(UUID id) {
        log.info("starting delete customer by id");
        customerRepo.deleteById(id);
        log.info("finished delete customer by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search customer");
        return toDTO(customerRepo
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("customer with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о покупателе с id = " + id);
                }));
    }

    private CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    private Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
}
