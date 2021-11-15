package ru.i.sys.labs.dto;

import lombok.*;
import ru.i.sys.labs.entity.CustomerBasket;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.entity.Product;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BasketProductDTO {

    private UUID id;
    private Integer countProduct;
    private Product product;
    private CustomerBasket customerBasket;
    private Order order;

}
