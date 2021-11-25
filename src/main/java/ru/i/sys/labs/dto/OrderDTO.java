package ru.i.sys.labs.dto;

import lombok.*;
import ru.i.sys.labs.entity.Delivery;
import ru.i.sys.labs.entity.StatusPay;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private UUID id;
    @Enumerated(EnumType.STRING)
    private StatusPay status;
    private Delivery delivery;
    private BigDecimal cost;
    private LocalDate date;
}
