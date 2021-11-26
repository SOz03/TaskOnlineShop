package ru.i.sys.labs.service;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Service
public class BasketProductService {

    private final BasketProductRepositoryDAO basketProductRepositoryDAO;
    private final ModelMapper modelMapper;

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

    public BasketProductDTO getBasketProductById(UUID id) throws ResourceNotFoundException {
        log.info("get basket product");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BasketProductDTO updateBasketProduct(UUID id, BasketProductDTO basketProductDTOUpdate) throws ResourceNotFoundException {

        BasketProduct basketProduct = basketProductRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("basket product with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о продуктах в корзине с id = " + id);
                });

        basketProduct.setOrder(basketProductDTOUpdate.getOrder());
        basketProduct.setCountProduct(basketProductDTOUpdate.getCountProduct());
        basketProduct.setCustomerBasket(basketProductDTOUpdate.getCustomerBasket());
        basketProduct.setProduct(basketProductDTOUpdate.getProduct());

        log.info("save basket product");

        return toDTO(basketProductRepositoryDAO.save(basketProduct));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBasketProduct(UUID id) {
        log.info("starting delete basket product by id");
        basketProductRepositoryDAO.deleteById(id);
        log.info("finished delete basket product by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BasketProductDTO findByID(UUID id) throws ResourceNotFoundException {
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
