package ru.i.sys.labs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "first_name")
    private String FIO;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Column(name = "address")
    private String address;

    public Customer() {
    }

    public Customer(String FIO, String phoneNumber,
                    Date dateBirth, String address) {
        this.id = UUID.randomUUID();
        this.FIO = FIO;
        this.phoneNumber = phoneNumber;
        this.dateBirth = dateBirth;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (getId() != null ? !getId().equals(customer.getId()) : customer.getId() != null) return false;
        if (getFIO() != null ? !getFIO().equals(customer.getFIO()) : customer.getFIO() != null) return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(customer.getPhoneNumber()) : customer.getPhoneNumber() != null)
            return false;
        if (getDateBirth() != null ? !getDateBirth().equals(customer.getDateBirth()) : customer.getDateBirth() != null)
            return false;
        return getAddress() != null ? getAddress().equals(customer.getAddress()) : customer.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFIO() != null ? getFIO().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getDateBirth() != null ? getDateBirth().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateBirth=" + dateBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
