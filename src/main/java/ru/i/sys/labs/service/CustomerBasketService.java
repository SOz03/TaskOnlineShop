package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.dto.CustomerBasketDTO;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.CustomerBasketRepositoryDAO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerBasketService {

    private final CustomerBasketRepositoryDAO customerBasketRepositoryDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerBasketService(CustomerBasketRepositoryDAO customerBasketRepositoryDAO,
                                 ModelMapper modelMapper) {
        this.customerBasketRepositoryDAO = customerBasketRepositoryDAO;
        this.modelMapper = modelMapper;
    }

    public List<CustomerBasketDTO> getAllCustomerBaskets() {
        log.info("list customer basket");
        return customerBasketRepositoryDAO
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerBasketDTO createCustomerBasket(CustomerBasketDTO customerBasketDTO) {
        log.info("starting customer basket creation");
        return toDTO(customerBasketRepositoryDAO.save(toEntity(customerBasketDTO)));
    }


    public CustomerBasketDTO getCustomerBasketById(UUID id) throws ResourceNotFoundException {
        log.info("get customer basket");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerBasketDTO updateCustomerBasket(UUID id, CustomerBasketDTO customerBasketDTOUpdate) throws ResourceNotFoundException {
        CustomerBasket customerBasket = customerBasketRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("customer basket with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о корзине покупателя с id = " + id);
                });

        customerBasket.setCustomer(customerBasketDTOUpdate.getCustomer());

        log.info("save customer basket");

        return toDTO(customerBasketRepositoryDAO.save(customerBasket));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomerBasket(UUID id) {
        log.info("starting delete customer basket by id");
        customerBasketRepositoryDAO.deleteById(id);
        log.info("finished delete customer basket by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerBasketDTO findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search customer basket");
        return toDTO(customerBasketRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("customer basket with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о корзине покупателя с id = " + id);
                }));
    }

    private CustomerBasketDTO toDTO(CustomerBasket customerBasket) {
        return modelMapper.map(customerBasket, CustomerBasketDTO.class);
    }

    private CustomerBasket toEntity(CustomerBasketDTO customerBasketDTO) {
        return modelMapper.map(customerBasketDTO, CustomerBasket.class);
    }
}
