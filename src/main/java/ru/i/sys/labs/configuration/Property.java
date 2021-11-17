package ru.i.sys.labs.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

//TODO Название сервиса не несет никакой смысловой нагрузки
@ConfigurationProperties(prefix = "spring.application.message-settings.message-type")
public class Property {

    private String scheduled;
    private boolean payQ;

    public boolean isPayQ() {
        return payQ;
    }

    public void setPayQ(boolean payQ) {
        this.payQ = payQ;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }
}
