package ru.i.sys.labs.service;

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
@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepositoryDAO productRepositoryDAO;

    @Autowired
    public ProductService(ProductRepositoryDAO productRepositoryDAO,
                          ModelMapper modelMapper) {
        this.productRepositoryDAO = productRepositoryDAO;
        this.modelMapper = modelMapper;
    }

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

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO getProductById(UUID id) throws ResourceNotFoundException {
        log.info("Get product");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO updateProduct(UUID id, ProductDTO productDTOUpdate) throws ResourceNotFoundException {
        ProductDTO productDTO = findByID(id);

        productDTO.setName(productDTOUpdate.getName());
        productDTO.setPrice(productDTOUpdate.getPrice());
        productDTO.setProductionDate(productDTOUpdate.getProductionDate());
        productDTO.setDescription(productDTOUpdate.getDescription());

        log.info("save product");

        return toDTO(productRepositoryDAO.save(toEntity(productDTO)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProduct(UUID id) {
        log.info("starting delete product by id");
        productRepositoryDAO.deleteById(id);
        log.info("finished delete product by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    ProductDTO findByID(UUID id) throws ResourceNotFoundException {
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
