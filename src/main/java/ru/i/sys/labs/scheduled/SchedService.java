package ru.i.sys.labs.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;

import java.util.List;

@Service
public class SchedService {

    private final OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    public SchedService(OrderRepositoryDAO orderRepositoryDAO) {
        this.orderRepositoryDAO = orderRepositoryDAO;
    }

    public List<Order> findListNoPay(){
        return orderRepositoryDAO.findListNoPay();
    }
}
