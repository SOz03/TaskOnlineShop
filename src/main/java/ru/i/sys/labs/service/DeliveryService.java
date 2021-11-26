package ru.i.sys.labs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.dto.DeliveryDTO;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.DeliveryRepositoryDAO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepositoryDAO deliveryRepositoryDAO;
    private final ModelMapper modelMapper;

    public List<DeliveryDTO> getAllDelivery() {
        log.info("list delivery");
        return deliveryRepositoryDAO
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        log.info("starting delivery creation");

        Delivery delivery = toEntity(deliveryDTO);
        Delivery newDelivery = deliveryRepositoryDAO.save(delivery);

        return toDTO(newDelivery);
    }


    public DeliveryDTO getDeliveryById(UUID id) throws ResourceNotFoundException {
        log.info("get delivery");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DeliveryDTO updateDelivery(UUID id, DeliveryDTO deliveryDTOUpdate) throws ResourceNotFoundException {
        Delivery delivery = deliveryRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("delivery with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о доставке с id = " + id);
                });
        delivery.setCost(deliveryDTOUpdate.getCost());
        delivery.setName(deliveryDTOUpdate.getName());
        delivery.setTimeDelivery(deliveryDTOUpdate.getTimeDelivery());

        log.info("save delivery");

        return toDTO(deliveryRepositoryDAO.save(delivery));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDelivery(UUID id) {
        log.info("starting delete delivery by id");
        deliveryRepositoryDAO.deleteById(id);
        log.info("finished delete delivery by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DeliveryDTO findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search delivery");
        return toDTO(deliveryRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("delivery with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о доставке с id = " + id);
                }));
    }

    private DeliveryDTO toDTO(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryDTO.class);
    }

    private Delivery toEntity(DeliveryDTO deliveryDTO) {
        return modelMapper.map(deliveryDTO, Delivery.class);
    }
}
