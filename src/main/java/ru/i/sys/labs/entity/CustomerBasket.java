package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_baskets")
public class CustomerBasket extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customers_id")
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerBasket that = (CustomerBasket) o;
        return Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer);
    }
}
