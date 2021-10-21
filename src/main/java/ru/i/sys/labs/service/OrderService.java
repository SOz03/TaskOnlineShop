package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;
import ru.i.sys.labs.timer.message.PayOrderTimer;
import ru.i.sys.labs.timer.service.SchedulerService;
import ru.i.sys.labs.timer.model.TimerInfo;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class OrderService {

    @Value("${spring.application.status.pay}")
    private boolean pay;

    private final SchedulerService scheduler;
    private final OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    public OrderService(OrderRepositoryDAO orderRepositoryDAO, SchedulerService scheduler) {
        this.orderRepositoryDAO = orderRepositoryDAO;
        this.scheduler = scheduler;
    }

    public List<Order> getAllOrders() {
        log.info("list orders");
        return orderRepositoryDAO.findAll();
    }

    public void createOrder(Order order) {
        log.info("starting product creation");
        orderRepositoryDAO.save(order);

        if(pay){
            TimerInfo info = new TimerInfo(3600000L, new Date(), 1);
            scheduler.schedule(PayOrderTimer.class, order, info);
        }

        log.info("finished product creation");
    }

    public Order getOrderById(UUID id) throws ResourceNotFoundException {
        log.info("get order");
        return findByID(id);
    }

    public Order updateOrder(UUID id, Order orderUpdate) throws ResourceNotFoundException {
        Order order = findByID(id);
        order.setCost(orderUpdate.getCost());
        order.setDate(orderUpdate.getDate());
        order.setDelivery(orderUpdate.getDelivery());
        log.info("save order");
        orderRepositoryDAO.save(order);
        return order;
    }

    public void deleteOrder(UUID id) throws ResourceNotFoundException {
        log.info("starting delete order by id");
        findByID(id);
        orderRepositoryDAO.deleteById(id);
        log.info("finished delete order by id");
    }

    private Order findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search order");
        return orderRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("order with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о заказе с id= " + id);
                });
    }

    public Order payOrder(UUID orderId, BigDecimal sum) throws ResourceNotFoundException {
        log.info("Payment started");
        Order order = findByID(orderId);
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getDate());
        calendar.add(Calendar.HOUR_OF_DAY, 1);

        if (order.getStatus().equals("не оплачен")) {
            if (sum.equals(order.getCost())) {
                if (pay) {
                    if (nowDate.before(calendar.getTime())) {
                        order.setStatus("оплачен");
                        updateOrder(orderId, order);
                        scheduler.deleteTimer(orderId.toString());
                    } else {
                        log.warn("Вышло время для оплаты");
                    }
                } else {
                    order.setStatus("оплачен");
                    updateOrder(orderId, order);
                }
            } else {
                log.info("Вы указали неверную сумму");
            }
        } else {
            log.info("Заказ уже оплачен");
        }

        log.info("Payment finished");
        return order;
    }
}
