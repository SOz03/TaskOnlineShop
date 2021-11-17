package ru.i.sys.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.i.sys.labs.entity.Order;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("Select o From Order o Where o.status = 'NOT_PAID'")
    List<Order> findListNoPay();

    //TODO написать метод для получения всех оплаченых заказов без использования @Query.
}
