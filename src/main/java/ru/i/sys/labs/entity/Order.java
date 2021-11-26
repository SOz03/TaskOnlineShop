package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPay status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return status == order.status && Objects.equals(delivery, order.delivery) && Objects.equals(cost, order.cost) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status, delivery, cost, date);
    }
}
