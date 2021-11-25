package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "first_name")
    private String FIO;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Column(name = "address")
    private String address;

}
