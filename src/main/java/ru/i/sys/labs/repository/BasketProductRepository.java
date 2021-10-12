package ru.i.sys.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.i.sys.labs.entity.BasketProduct;

import java.util.UUID;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, UUID> {

}
