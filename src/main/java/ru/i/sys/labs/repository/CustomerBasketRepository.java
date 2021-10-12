package ru.i.sys.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.i.sys.labs.entity.CustomerBasket;

import java.util.UUID;

@Repository
public interface CustomerBasketRepository extends JpaRepository<CustomerBasket, UUID> {
}
