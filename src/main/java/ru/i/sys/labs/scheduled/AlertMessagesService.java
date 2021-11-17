package ru.i.sys.labs.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;

import java.util.List;

//TODO :D Название сервиса не несет никакой смысловой нагрузки. с тем же успехом можно было назвать blablaService
@Service
public class AlertMessagesService {

    private final OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    public AlertMessagesService(OrderRepositoryDAO orderRepositoryDAO) {
        this.orderRepositoryDAO = orderRepositoryDAO;
    }

    public List<Order> findListNoPay(){
        return orderRepositoryDAO.findListNoPay();
    }

    public List<Order> findListPaid(){
        return orderRepositoryDAO.findListNoPay();
    }
}
