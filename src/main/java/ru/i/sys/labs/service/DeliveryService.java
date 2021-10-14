package ru.i.sys.labs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.DeliveryRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService {
    private final DeliveryRepositoryDAO deliveryRepositoryDAO;

    @Autowired
    public DeliveryService(DeliveryRepositoryDAO deliveryRepositoryDAO) {
        this.deliveryRepositoryDAO = deliveryRepositoryDAO;
    }

    public List<Delivery> getAllDelivery() {
        return deliveryRepositoryDAO.findAll();
    }

    public void createDelivery(Delivery delivery) {
        deliveryRepositoryDAO.save(delivery);
    }

    public Delivery getDeliveryById(UUID id) throws ResourceNotFoundException {
        return findByID(id);
    }

    public Delivery updateDelivery(UUID id, Delivery deliveryUpdate) throws ResourceNotFoundException {
        Delivery delivery = findByID(id);
        delivery.setCost(deliveryUpdate.getCost());
        delivery.setName(deliveryUpdate.getName());
        delivery.setTimeDelivery(deliveryUpdate.getTimeDelivery());
        deliveryRepositoryDAO.save(delivery);
        return delivery;
    }

    public void deleteDelivery(UUID id) throws ResourceNotFoundException {
        deliveryRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о доставке с id= " + id));
        deliveryRepositoryDAO.deleteById(id);
    }

    private Delivery findByID(UUID id) throws ResourceNotFoundException {
        return deliveryRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о доставке с id= " + id));
    }
}
