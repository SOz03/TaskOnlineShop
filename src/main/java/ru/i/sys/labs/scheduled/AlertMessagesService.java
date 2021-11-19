package ru.i.sys.labs.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.serviceDAO.CustomerRepositoryDAO;
import ru.i.sys.labs.serviceDAO.OrderRepositoryDAO;
import ru.i.sys.labs.specification.CustomerSpecification;

import java.util.Date;
import java.util.List;

@Service
public class AlertMessagesService {

    private final OrderRepositoryDAO orderRepo;
    private final CustomerRepositoryDAO customerRepo;

    @Autowired
    public AlertMessagesService(OrderRepositoryDAO orderRepositoryDAO,
                                CustomerRepositoryDAO customerRepositoryDAO) {
        this.orderRepo = orderRepositoryDAO;
        this.customerRepo = customerRepositoryDAO;
    }

    public List<Order> findListNoPay() {
        return orderRepo.findListNoPay();
    }

    public List<Order> findListPaid() {
        return orderRepo.findListPaid();
    }

    public List<Customer> customerHasBirthday() {
        Date today = new Date();
        Specification<Customer> specification = Specification.where(CustomerSpecification.customerHasBirthday(today));

        return customerRepo.findAll(specification);
    }

}
