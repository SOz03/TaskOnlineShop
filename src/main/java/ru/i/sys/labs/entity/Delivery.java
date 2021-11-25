package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "time_delivery")
    private Date timeDelivery;

}
