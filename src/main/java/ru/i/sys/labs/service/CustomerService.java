package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.dto.CustomerDTO;
import ru.i.sys.labs.dto.ProductDTO;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerRepositoryDAO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepositoryDAO customerRepositoryDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepositoryDAO customerRepositoryDAO, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.customerRepositoryDAO = customerRepositoryDAO;
    }

    public List<CustomerDTO> getAllCustomers() {
        log.info("list customers");
        return customerRepositoryDAO
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        log.info("starting customer creation");
        return toDTO(customerRepositoryDAO.save(toEntity(customerDTO)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO getCustomerById(UUID id) throws ResourceNotFoundException {
        log.info("get customer");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO updateCustomer(UUID id, CustomerDTO customerDTOUpdate) throws ResourceNotFoundException {
        CustomerDTO customerDTO = findByID(id);
        customerDTO.setFIO(customerDTOUpdate.getFIO());
        customerDTO.setDateBirth(customerDTOUpdate.getDateBirth());
        customerDTO.setPhoneNumber(customerDTOUpdate.getPhoneNumber());
        customerDTO.setAddress(customerDTOUpdate.getAddress());

        log.info("save customer");

        return toDTO(customerRepositoryDAO.save(toEntity(customerDTO)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(UUID id) {
        log.info("starting delete customer by id");
        customerRepositoryDAO.deleteById(id);
        log.info("finished delete customer by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    CustomerDTO findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search customer");
        return toDTO(customerRepositoryDAO
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
