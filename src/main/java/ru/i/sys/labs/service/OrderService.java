package ru.i.sys.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.configuration.ScheduledSettings;
import ru.i.sys.labs.dto.OrderDTO;
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
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    private final ScheduledSettings scheduledSettings;
    private final SchedulerService scheduler;
    private final OrderRepositoryDAO orderRepositoryDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(ScheduledSettings scheduledSettings, OrderRepositoryDAO orderRepositoryDAO, SchedulerService scheduler,
                        ModelMapper modelMapper) {
        this.scheduledSettings = scheduledSettings;
        this.orderRepositoryDAO = orderRepositoryDAO;
        this.scheduler = scheduler;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        log.info("list orders");

        return orderRepositoryDAO
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO createOrder(Order order) {
        log.info("starting product creation");
        OrderDTO orderResponse = toDTO(orderRepositoryDAO.save(order));
        startTimer(order);//QUARTZ

        return orderResponse;
    }

    private void startTimer(Order order){
        if (scheduledSettings.isPayQ()) {
            TimerInfo info = new TimerInfo(3600000L, new Date(), 1);
            scheduler.schedule(PayOrderTimer.class, order, info);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO getOrderById(UUID id) throws ResourceNotFoundException {
        log.info("get order");
        return findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO updateOrder(UUID id, OrderDTO orderDTOUpdate) throws ResourceNotFoundException {
        Order order = orderRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("order with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о заказе с id= " + id);
                });
        order.setCost(orderDTOUpdate.getCost());
        order.setDate(orderDTOUpdate.getDate());
        order.setDelivery(orderDTOUpdate.getDelivery());
        order.setStatus(orderDTOUpdate.getStatus());
        log.info("save order");

        return toDTO(orderRepositoryDAO.save(order));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOrder(UUID id) {
        log.info("starting delete order by id");
        orderRepositoryDAO.deleteById(id);
        log.info("finished delete order by id");
    }


    private OrderDTO findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search order {}", id);
        Order order = orderRepositoryDAO
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("order with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о заказе с id= " + id);
                });
        return toDTO(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO payOrder(UUID orderId, BigDecimal sum) throws ResourceNotFoundException {
        log.info("Payment started");
        OrderDTO newOrder = findByID(orderId);
        Order order = toEntity(newOrder);

        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getDate());
        calendar.add(Calendar.HOUR_OF_DAY, 1);

        if (order.getStatus().equals(StatusPay.NOT_PAID)) {
            if (sum.equals(order.getCost())) {
                if (scheduledSettings.isPayQ()) {
                    if (nowDate.before(calendar.getTime())) {
                        order.setStatus(StatusPay.PAID);
                        updateOrder(orderId, toDTO(order));

                        //QUARTZ
                        scheduler.deleteTimer(orderId.toString());
                    } else {
                        log.warn("Time out for payment");
                    }
                } else {
                    order.setStatus(StatusPay.PAID);
                    updateOrder(orderId, toDTO(order));
                }
            } else {
                log.info("Wrong amount");
            }
        } else {
            log.warn("Order paid");
        }

        log.info("Payment finished");
        return toDTO(order);
    }

    private OrderDTO toDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    private Order toEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }
}
