package ru.i.sys.labs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.i.sys.labs.dto.OrderDTO;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.entity.StatusPay;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepositoryDAO orderRepo;
    private final ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> findListNoPay() {
        log.info("find list not paid orders");
        List<Order> ordersNoPaid = orderRepo.findListNoPay();
        if (ordersNoPaid.isEmpty()) {
            log.info("all orders paid");
        }

        return ordersNoPaid;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> findListPaid() {
        log.info("find list paid orders");
        List<Order> ordersPaid = orderRepo.findListPaid();
        if (ordersPaid.isEmpty()) {
            log.info("no paid orders");
        }

        return ordersPaid;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderDTO> getAllOrders() {
        log.info("list orders");

        return orderRepo
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO createOrder(Order order) {
        log.info("starting product creation");
        return toDTO(orderRepo.save(order));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO getOrderById(UUID id) throws ResourceNotFoundException {
        log.info("get order");
        return toDTO(findByID(id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO updateOrder(UUID id, OrderDTO orderDTOUpdate) throws ResourceNotFoundException {
        Order order = findByID(id);

        order.setCost(orderDTOUpdate.getCost());
        order.setDate(orderDTOUpdate.getDate());
        order.setDelivery(orderDTOUpdate.getDelivery());
        order.setStatus(orderDTOUpdate.getStatus());
        log.info("save order");

        return toDTO(orderRepo.save(order));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOrder(UUID id) {
        log.info("starting delete order by id");
        orderRepo.deleteById(id);
        log.info("finished delete order by id");
    }


    private Order findByID(UUID id) throws ResourceNotFoundException {
        log.info("Search order {}", id);
        return orderRepo
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("order with id = {} not found", id);
                    return new ResourceNotFoundException("Нет данных о заказе с id= " + id);
                });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDTO payOrder(UUID orderId, BigDecimal sum) throws ResourceNotFoundException {
        log.info("Payment started");
        Order order = findByID(orderId);

        if (order.getStatus().equals(StatusPay.NOT_PAID)) {
            if (sum.equals(order.getCost())) {
                if (LocalDate.now().isBefore(order.getDate().plusDays(1))) {
                    order.setStatus(StatusPay.PAID);
                    updateOrder(orderId, toDTO(order));
                } else {
                    log.warn("Time out for payment");
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
}
