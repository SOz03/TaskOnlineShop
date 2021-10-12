package ru.i.sys.labs.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "time_delivery")
    private Date timeDelivery;

    public Delivery() {}

    public Delivery(String name, BigDecimal cost, Date timeDelivery) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cost = cost;
        this.timeDelivery = timeDelivery;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getTimeDelivery() {
        return timeDelivery;
    }

    public void setTimeDelivery(Date timeDelivery) {
        this.timeDelivery = timeDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;

        Delivery delivery = (Delivery) o;

        if (getId() != null ? !getId().equals(delivery.getId()) : delivery.getId() != null) return false;
        if (getName() != null ? !getName().equals(delivery.getName()) : delivery.getName() != null) return false;
        if (getCost() != null ? !getCost().equals(delivery.getCost()) : delivery.getCost() != null) return false;
        return getTimeDelivery() != null ? getTimeDelivery().equals(delivery.getTimeDelivery()) : delivery.getTimeDelivery() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCost() != null ? getCost().hashCode() : 0);
        result = 31 * result + (getTimeDelivery() != null ? getTimeDelivery().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", timeDelivery=" + timeDelivery +
                '}';
    }
}
