package ru.i.sys.labs.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_baskets")
public class CustomerBasket extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customers_id")
    private Customer customer;

    public CustomerBasket() {
    }

    public CustomerBasket(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerBasket)) return false;

        CustomerBasket that = (CustomerBasket) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getCustomer() != null ? getCustomer().equals(that.getCustomer()) : that.getCustomer() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerBasket{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}
