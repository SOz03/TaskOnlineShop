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

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        checkHappyBirthdayInclusionIfOnStartProcess(prefix, variation);
        checkListUnpaidOrdersInclusionIfOnStartProcess(prefix, variation);
        checkListPaidOrdersInclusionIfOnStartProcess(prefix, variation);
        checkMessageInclusionIfOnStartProcess(prefix, variation);
    }

    public void checkHappyBirthdayInclusionIfOnStartProcess(String prefix, String variation) {
        if ((happyBirthdayProperty.isEnabled()) && (property.isEnabled())) {
            String[] arrayHowSend = happyBirthdayProperty.getHow().split(property.getSplit());
            for (String way : setWay(arrayHowSend)) {
                if (way.equals(variation)) {
                    happyBirthday(prefix);
                }
            }
        }
    }

    public void checkListUnpaidOrdersInclusionIfOnStartProcess(String prefix, String variation) {
        if ((ordersUnpaidProperty.isEnabled()) && (property.isEnabled())) {
            String[] arrayHowSend = ordersUnpaidProperty.getHow().split(property.getSplit());
            for (String way : setWay(arrayHowSend)) {
                if (way.equals(variation)) {
                    listUnpaidOrders(prefix);
                }
            }
        }
    }

    public void checkListPaidOrdersInclusionIfOnStartProcess(String prefix, String variation) {
        if ((ordersPaidProperty.isEnabled()) && (property.isEnabled())) {
            String[] arrayHowSend = ordersPaidProperty.getHow().split(property.getSplit());
            for (String way : setWay(arrayHowSend)) {
                if (way.equals(variation)) {
                    listPaidOrders(prefix);
                }
            }
        }
    }

    public void checkMessageInclusionIfOnStartProcess(String prefix, String variation) {
        if ((messageProperty.isEnabled()) && (property.isEnabled()) && !messageProperty.getText().equals("")) {
            String[] arrayHowSend = messageProperty.getHow().split(property.getSplit());
            for (String way : setWay(arrayHowSend)) {
                if (way.equals(variation) && messageProperty.getRecipient().equals("")) {
                    message(prefix, messageProperty.getText());
                } else if (way.equals(variation) && !messageProperty.getRecipient().equals("")) {
                    CustomerDTO customerDTO;
                    try {
                        customerDTO = customerService.getCustomerByPhoneNumber(messageProperty.getRecipient());
                        message(prefix, messageProperty.getText(), customerDTO.getFIO());
                    } catch (ResourceNotFoundException exception) {
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
        log.info("{}Здравствуйте, {}", prefix, recipient);
        log.info("{} {}", prefix, text);
    }

    private void message(String prefix, String text) {
        log.info("{} {}", prefix, text);
    }

    private Set<String> setWay(String[] ways){
        return Arrays.stream(ways).collect(Collectors.toSet());
    }
}
