package ru.i.sys.labs.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.entity.Customer;

import java.util.List;

@Slf4j
@Component
public class BirthdayMessage implements Message {

    private final AlertMessagesService service;

    @Autowired
    public BirthdayMessage(AlertMessagesService schedulerService) {
        this.service = schedulerService;
    }

    @Override
    public void sendMessage() {
        List<Customer> customers = service.customerHasBirthday();
        if (customers.isEmpty()) {
            log.info("Birthday-message || Unfortunately, nobody has a birthday today.");
        } else {
            for (Customer customer : customers) {
                log.info("Birthday-message || Today is birthday {}", customer.getFIO());
            }
        }

    }

}
