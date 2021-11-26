package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    @Size(max = 50, message = "Name delivery does not exceed 50 characters")
    @NotNull(message = "Name delivery cannot be null")
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;

    @NotNull
    @Column(name = "time_delivery")
    private Date timeDelivery;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(name, delivery.name) && Objects.equals(cost, delivery.cost) && Objects.equals(timeDelivery, delivery.timeDelivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, cost, timeDelivery);
    }
}
