package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baskets_products")
public class BasketProduct extends BaseEntity {

    @Min(1)
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BasketProduct that = (BasketProduct) o;
        return Objects.equals(countProduct, that.countProduct) && Objects.equals(product, that.product) && Objects.equals(customerBasket, that.customerBasket) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countProduct, product, customerBasket, order);
    }
}
