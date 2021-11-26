package ru.i.sys.labs.serviceDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.repository.DeliveryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryRepositoryDAO {

    private final DeliveryRepository deliveryRepository;

    public List<Delivery> findAll() {
        log.info("executing a database query 'findAll'");
        return deliveryRepository.findAll();
    }

    public Delivery save(Delivery delivery) {
        log.info("executing a database query 'save'");
        return deliveryRepository.save(delivery);
    }

    public Optional<Delivery> findById(UUID id) {
        log.info("executing a database query 'findById'");
        return deliveryRepository.findById(id);
    }

    public void deleteById(UUID id) {
        log.info("executing a database query 'deleteById'");
        deliveryRepository.deleteById(id);
        log.info("data received");
    }
}
