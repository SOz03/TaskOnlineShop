package ru.i.sys.labs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.dto.ProductDTO;
import ru.i.sys.labs.entity.Product;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.ProductRepositoryDAO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepositoryDAO productRepositoryDAO;

    public List<ProductDTO> getAllProducts() {
        log.info("list products");

        return productRepositoryDAO
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO createProduct(ProductDTO productDTO) {
        log.info("starting product creation");

        Product product = toEntity(productDTO);
        Product newProduct = productRepositoryDAO.save(product);

        return toDTO(newProduct);
    }

    public ProductDTO getProductById(UUID id) throws ResourceNotFoundException {
        log.info("Get product");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO updateProduct(UUID id, ProductDTO productDTOUpdate) throws ResourceNotFoundException {
        Product product = productRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("product with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о продукте с id = " + id);
                });
        product.setName(productDTOUpdate.getName());
        product.setPrice(productDTOUpdate.getPrice());
        product.setProductionDate(productDTOUpdate.getProductionDate());
        product.setDescription(productDTOUpdate.getDescription());

        log.info("save product");

        return toDTO(productRepositoryDAO.save(product));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProduct(UUID id) {
        log.info("starting delete product by id");
        productRepositoryDAO.deleteById(id);
        log.info("finished delete product by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search product");

        Product product = productRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("product with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о продукте с id = " + id);
                });

        return toDTO(product);
    }

    private ProductDTO toDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

}
