package ru.i.sys.labs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.DeliveryRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService {

    private final Logger log = LoggerFactory.getLogger(DeliveryService.class);
    private final DeliveryRepositoryDAO deliveryRepositoryDAO;

    @Autowired
    public DeliveryService(DeliveryRepositoryDAO deliveryRepositoryDAO) {
        this.deliveryRepositoryDAO = deliveryRepositoryDAO;
    }

    public List<Delivery> getAllDelivery() {
        log.info("list delivery");
        return deliveryRepositoryDAO.findAll();
    }

    public void createDelivery(Delivery delivery) {
        log.info("starting delivery creation");
        deliveryRepositoryDAO.save(delivery);
        log.info("finished delivery creation");
    }

    public Delivery getDeliveryById(UUID id) throws ResourceNotFoundException {
        log.info("get delivery");
        return findByID(id);
    }

    public Delivery updateDelivery(UUID id, Delivery deliveryUpdate) throws ResourceNotFoundException {
        Delivery delivery = findByID(id);
        delivery.setCost(deliveryUpdate.getCost());
        delivery.setName(deliveryUpdate.getName());
        delivery.setTimeDelivery(deliveryUpdate.getTimeDelivery());
        log.info("save delivery");
        deliveryRepositoryDAO.save(delivery);
        return delivery;
    }

    public void deleteDelivery(UUID id) throws ResourceNotFoundException {
        log.info("starting delete delivery by id");
        findByID(id);
        deliveryRepositoryDAO.deleteById(id);
        log.info("finished delete delivery by id");
    }

    private Delivery findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search delivery");
        return deliveryRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("delivery with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о доставке с id = " + id);
                });
    }
}
