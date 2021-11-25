package ru.i.sys.labs.messages.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.notification.send.orders-paid")
public class OrdersPaidProperty extends Property {
}
