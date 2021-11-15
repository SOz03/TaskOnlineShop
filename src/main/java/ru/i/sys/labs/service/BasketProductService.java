package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.dto.BasketProductDTO;
import ru.i.sys.labs.dto.ProductDTO;
import ru.i.sys.labs.entity.BasketProduct;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.BasketProductRepositoryDAO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BasketProductService {

    private final BasketProductRepositoryDAO basketProductRepositoryDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public BasketProductService(BasketProductRepositoryDAO basketProductRepositoryDAO,
                                ModelMapper modelMapper) {
        this.basketProductRepositoryDAO = basketProductRepositoryDAO;
        this.modelMapper = modelMapper;
    }

    public List<BasketProductDTO> getAllBasketProducts() {
        log.info("list basket products");
        return basketProductRepositoryDAO
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BasketProductDTO createBasketProduct(BasketProductDTO basketProductDTO) {
        log.info("starting basket product creation");
        return toDTO(basketProductRepositoryDAO.save(toEntity(basketProductDTO)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BasketProductDTO getBasketProductById(UUID id) throws ResourceNotFoundException {
        log.info("get basket product");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BasketProductDTO updateBasketProduct(UUID id, BasketProductDTO basketProductDTOUpdate) throws ResourceNotFoundException {
        BasketProductDTO basketProductDTO = findByID(id);
        basketProductDTO.setOrder(basketProductDTOUpdate.getOrder());
        basketProductDTO.setCountProduct(basketProductDTOUpdate.getCountProduct());
        basketProductDTO.setCustomerBasket(basketProductDTOUpdate.getCustomerBasket());
        basketProductDTO.setProduct(basketProductDTOUpdate.getProduct());

        log.info("save basket product");

        return toDTO(basketProductRepositoryDAO.save(toEntity(basketProductDTO)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBasketProduct(UUID id) {
        log.info("starting delete basket product by id");
        basketProductRepositoryDAO.deleteById(id);
        log.info("finished delete basket product by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    BasketProductDTO findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search basket product");
        return toDTO(basketProductRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("basket product with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о продуктах в корзине с id = " + id);
                }));
    }

    private BasketProductDTO toDTO(BasketProduct basketProduct) {
        return modelMapper.map(basketProduct, BasketProductDTO.class);
    }

    private BasketProduct toEntity(BasketProductDTO basketProductDTO) {
        return modelMapper.map(basketProductDTO, BasketProduct.class);
    }
}
