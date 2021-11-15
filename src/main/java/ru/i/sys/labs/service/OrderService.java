package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.configuration.Property;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.entity.StatusPay;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;
import ru.i.sys.labs.timer.message.PayOrderTimer;
import ru.i.sys.labs.timer.model.TimerInfo;
import ru.i.sys.labs.timer.service.SchedulerService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderService {

    private final Property property;
    private final SchedulerService scheduler;
    private final OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    public OrderService(Property property, OrderRepositoryDAO orderRepositoryDAO, SchedulerService scheduler) {
        this.property = property;
        this.orderRepositoryDAO = orderRepositoryDAO;
        this.scheduler = scheduler;
    }

    public List<Order> getAllOrders() {
        log.info("list orders");
        return orderRepositoryDAO.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder(Order order) {
        log.info("starting product creation");
        orderRepositoryDAO.save(order);

        //QUARTZ
        if (property.isPayQ()) {
            TimerInfo info = new TimerInfo(3600000L, new Date(), 1);
            scheduler.schedule(PayOrderTimer.class, order, info);
        }

        log.info("finished product creation");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order getOrderById(UUID id) throws ResourceNotFoundException {
        log.info("get order");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order updateOrder(UUID id, Order orderUpdate) throws ResourceNotFoundException {
        Order order = findByID(id);
        order.setCost(orderUpdate.getCost());
        order.setDate(orderUpdate.getDate());
        order.setDelivery(orderUpdate.getDelivery());
        log.info("save order");
        orderRepositoryDAO.save(order);
        return order;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOrder(UUID id) {
        log.info("starting delete order by id");
        orderRepositoryDAO.deleteById(id);
        log.info("finished delete order by id");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    Order findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search order");
        return orderRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("order with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о заказе с id= " + id);
                });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order payOrder(UUID orderId, BigDecimal sum) throws ResourceNotFoundException {
        log.info("Payment started");
        Order order = findByID(orderId);
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getDate());
        calendar.add(Calendar.HOUR_OF_DAY, 1);

        if (order.getStatus().equals(StatusPay.NOT_PAID)) {
            if (sum.equals(order.getCost())) {
                if (property.isPayQ()) {
                    if (nowDate.before(calendar.getTime())) {
                        order.setStatus(StatusPay.PAID);
                        updateOrder(orderId, order);

                        //QUARTZ
                        scheduler.deleteTimer(orderId.toString());
                    } else {
                        log.warn("Time out for payment");
                    }
                } else {
                    order.setStatus(StatusPay.PAID);
                    updateOrder(orderId, order);
                }
            } else {
                log.info("Wrong amount");
            }
        } else {
            log.warn("Order paid");
        }

        log.info("Payment finished");
        return order;
    }
}
