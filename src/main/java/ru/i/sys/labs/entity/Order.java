package ru.i.sys.labs.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPay status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "date")
    private Date date;

    public Order() {
    }

    public Order(Delivery delivery, BigDecimal cost, Date date, StatusPay status) {
        this.delivery = delivery;
        this.cost = cost;
        this.date = date;
        this.status = status;
    }

    public StatusPay getStatus() {
        return status;
    }

    public void setStatus(StatusPay status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (getStatus() != null ? !getStatus().equals(order.getStatus()) : order.getStatus() != null) return false;
        if (getDelivery() != null ? !getDelivery().equals(order.getDelivery()) : order.getDelivery() != null)
            return false;
        if (getCost() != null ? !getCost().equals(order.getCost()) : order.getCost() != null) return false;
        return getDate() != null ? getDate().equals(order.getDate()) : order.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getDelivery() != null ? getDelivery().hashCode() : 0);
        result = 31 * result + (getCost() != null ? getCost().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status.getName() + '\'' +
                ", delivery=" + delivery +
                ", cost=" + cost +
                ", date=" + date +
                '}';
    }
}
