package ru.i.sys.labs.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.repository.DeliveryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryRepositoryDAO {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryRepositoryDAO(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public Optional<Delivery> findById(UUID id) {
        return deliveryRepository.findById(id);
    }

    public void deleteById(UUID id) {
        deliveryRepository.deleteById(id);
    }
}
