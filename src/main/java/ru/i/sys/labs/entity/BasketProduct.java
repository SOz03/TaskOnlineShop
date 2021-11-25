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
@Table(name = "baskets_products")
public class BasketProduct extends BaseEntity {

    @Column(name = "count_product")
    private Integer countProduct;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_baskets_id")
    private CustomerBasket customerBasket;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private Order order;

}
