package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
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

}
