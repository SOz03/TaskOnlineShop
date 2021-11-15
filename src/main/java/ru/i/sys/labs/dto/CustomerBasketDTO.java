package ru.i.sys.labs.dto;

import lombok.*;
import ru.i.sys.labs.entity.Customer;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBasketDTO {

    private UUID id;
    private Customer customer;
}
