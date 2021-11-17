package ru.i.sys.labs.entity;

import javax.persistence.*;

@Entity
@Table(name = "baskets_products")
public class BasketProduct extends BaseEntity {

    //TODO ++++ хорошая практика когда есть BaseEntity в которой есть общие аттрибуты типа id

    @Column(name = "count_product")
    private Integer countProduct;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_baskets_id")
    private CustomerBasket customerBasket;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private Order order;

    public BasketProduct() {
    }

    public BasketProduct(Product product, CustomerBasket customerBasket,
                         Integer countProduct, Order order) {
        this.product = product;
        this.customerBasket = customerBasket;
        this.countProduct = countProduct;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerBasket getCustomerBasket() {
        return customerBasket;
    }

    public void setCustomerBasket(CustomerBasket customerBasket) {
        this.customerBasket = customerBasket;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketProduct)) return false;

        BasketProduct that = (BasketProduct) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getProduct() != null ? !getProduct().equals(that.getProduct()) : that.getProduct() != null) return false;
        if (getCustomerBasket() != null ? !getCustomerBasket().equals(that.getCustomerBasket()) : that.getCustomerBasket() != null)
            return false;
        if (getCountProduct() != null ? !getCountProduct().equals(that.getCountProduct()) : that.getCountProduct() != null)
            return false;
        return getOrder() != null ? getOrder().equals(that.getOrder()) : that.getOrder() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
        result = 31 * result + (getCustomerBasket() != null ? getCustomerBasket().hashCode() : 0);
        result = 31 * result + (getCountProduct() != null ? getCountProduct().hashCode() : 0);
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BasketProduct{" +
                "id=" + id +
                ", product=" + product +
                ", customerBasket=" + customerBasket +
                ", countProduct=" + countProduct +
                ", order=" + order +
                '}';
    }
}
