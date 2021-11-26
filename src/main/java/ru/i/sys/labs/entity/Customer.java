package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Size(max = 70, message = "FIO does not exceed 70 characters")
    @NotNull(message = "FIO cannot be null")
    @Column(name = "first_name")
    private String FIO;

    @Size(min = 11, max = 11)
    @NotNull(message = "enter your phone number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "date_birth")
    private Date dateBirth;

    @Size(max = 100, message = "Address does not exceed 70 characters")
    @NotNull
    @Column(name = "address")
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(FIO, customer.FIO) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(dateBirth, customer.dateBirth) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), FIO, phoneNumber, dateBirth, address);
    }
}
