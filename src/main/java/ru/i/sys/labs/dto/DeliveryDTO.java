package ru.i.sys.labs.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    private UUID id;
    private String name;
    private BigDecimal cost;
    private Date timeDelivery;
}
