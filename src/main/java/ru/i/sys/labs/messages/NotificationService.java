package ru.i.sys.labs.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.dto.CustomerDTO;
import ru.i.sys.labs.entity.Customer;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.exception.ResourceNotFoundException;
import ru.i.sys.labs.messages.property.*;
import ru.i.sys.labs.service.CustomerService;
import ru.i.sys.labs.service.OrderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.notification.enabled", havingValue = "true", matchIfMissing = true)
@Service
public class NotificationService {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final OrdersPaidProperty ordersPaidProperty;
    private final OrdersUnpaidProperty ordersUnpaidProperty;
    private final HappyBirthdayProperty happyBirthdayProperty;
    private final MessageProperty messageProperty;
    private final GeneralProperty property;


    public void handler(String prefix, String variation) {

        if ((happyBirthdayProperty.isEnabled()) && (property.isEnabled())) {

            String[] messages = happyBirthdayProperty.getHow().split(" ");
            for (String message : messages) {
                if (message.equals(variation)) {
                    happyBirthday(prefix);
                }
            }
        }

        if ((ordersPaidProperty.isEnabled()) && (property.isEnabled())) {
            String[] messages = ordersPaidProperty.getHow().split(" ");
            for (String message : messages) {
                if (message.equals(variation)) {
                    listPaidOrders(prefix);
                }
            }
        }

        if ((ordersUnpaidProperty.isEnabled()) && (property.isEnabled())) {
            String[] messages = ordersUnpaidProperty.getHow().split(" ");
            for (String message : messages) {
                if (message.equals(variation)) {
                    listUnpaidOrders(prefix);
                }
            }
        }

        if ((messageProperty.isEnabled()) && (property.isEnabled())) {
            String[] messages = messageProperty.getHow().split(" ");
            for (String message : messages) {
                if (message.equals(variation) && messageProperty.getRecipient().equals("")) {
                    message(prefix, messageProperty.getText());
                } else if (message.equals(variation) && !messageProperty.getRecipient().equals("")) {
                    CustomerDTO customerDTO;
                    try {
                        customerDTO = customerService.getCustomerByPhoneNumber(messageProperty.getRecipient());
                        message(prefix, messageProperty.getText(), customerDTO.getFIO());
                    } catch (ResourceNotFoundException exception){
                        log.warn("покупатель с таким номером не найден");
                    }

                }
            }
        }
    }

    private void happyBirthday(String prefix) {
        List<Customer> customers = customerService.findBirthday();
        if (customers.isEmpty()) {
            log.info("{}Unfortunately, nobody has a birthday today.", prefix);
        } else {
            for (Customer customer : customers) {
                log.info("{}HAPPY BIRTHDAY {}", prefix, customer.getFIO());
            }
        }
    }

    private void listUnpaidOrders(String prefix) {
        List<Order> ordersUnpaid = orderService.findListNoPay();
        for (Order order : ordersUnpaid) {
            log.warn("{}Order NOT paid with ID {} ", prefix, order.getId());
        }
    }

    private void listPaidOrders(String prefix) {
        List<Order> ordersPaid = orderService.findListPaid();
        for (Order order : ordersPaid) {
            log.info("{}Order PAID with ID {} ", prefix, order.getId());
        }
    }

    private void message(String prefix, String text, String recipient) {
        log.info("Здравствуйте, {}", recipient);
        log.info("{} {}", prefix, text);
    }

    private void message(String prefix, String text) {
        log.info("{} {}", prefix, text);
    }
}
