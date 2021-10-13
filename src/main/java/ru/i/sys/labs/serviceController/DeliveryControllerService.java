package ru.i.sys.labs.serviceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.DeliveryRepositoryDAO;

import java.util.List;
import java.util.UUID;

@Service
public class DeliveryControllerService {
    private final DeliveryRepositoryDAO deliveryRepositoryDAO;

    @Autowired
    public DeliveryControllerService(DeliveryRepositoryDAO deliveryRepositoryDAO) {
        this.deliveryRepositoryDAO = deliveryRepositoryDAO;
    }

    public List<Delivery> getAllDelivery() {
        return deliveryRepositoryDAO.findAll();
    }

    public ResponseEntity<Delivery> createDelivery(Delivery delivery) {
        deliveryRepositoryDAO.save(delivery);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Delivery> getDeliveryById(UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(findByID(id));
    }

    public ResponseEntity<Delivery> updateDelivery(UUID id, Delivery deliveryUpdate) throws ResourceNotFoundException {
        Delivery delivery = findByID(id);
        delivery.setCost(deliveryUpdate.getCost());
        delivery.setName(deliveryUpdate.getName());
        delivery.setTimeDelivery(deliveryUpdate.getTimeDelivery());
        deliveryRepositoryDAO.save(delivery);
        return ResponseEntity.ok().body(delivery);
    }

    public ResponseEntity<Delivery> deleteDelivery(UUID id) throws ResourceNotFoundException {
        deliveryRepositoryDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Нет данных о доставке с id= " + id));
        deliveryRepositoryDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private Delivery findByID(UUID id) throws ResourceNotFoundException {
        return deliveryRepositoryDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет данных о доставке с id= " + id));
    }
}
