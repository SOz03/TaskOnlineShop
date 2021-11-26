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

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Size(max = 50, message = "Name product does not exceed 50 characters")
    @NotNull(message = "Name product cannot be null")
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "production_date")
    private Date productionDate;

    @Size(max = 250)
    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(productionDate, product.productionDate) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, price, productionDate, description);
    }
}
