package com.hsb.rms.domain;

import com.hsb.rms.domain.enumeration.PayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "counter")
    private String counter;

    @NotNull
    @Column(name = "bill", nullable = false)
    private Long bill = 0L;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "paid_id", nullable = false)
    private PayType paidId;

    @Column(name = "instruction")
    private String instruction;

    @ManyToOne(optional = false)
    @NotNull
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User servedBy;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public Order id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounter() {
        return this.counter;
    }

    public Order counter(String counter) {
        this.setCounter(counter);
        return this;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public Long getBill() {
        return this.bill;
    }

    public Order bill(Long bill) {
        this.setBill(bill);
        return this;
    }

    public void setBill(Long bill) {
        this.bill = bill;
    }

    public PayType getPaidId() {
        return this.paidId;
    }

    public Order paidId(PayType paidId) {
        this.setPaidId(paidId);
        return this;
    }

    public void setPaidId(PayType paidId) {
        this.paidId = paidId;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public Order instruction(String instruction) {
        this.setInstruction(instruction);
        return this;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public User getCustomer() {
        return this.customer;
    }

    public void setCustomer(User user) {
        this.customer = user;
    }

    public Order customer(User user) {
        this.setCustomer(user);
        return this;
    }

    public User getServedBy() {
        return this.servedBy;
    }

    public void setServedBy(User user) {
        this.servedBy = user;
    }

    public Order servedBy(User user) {
        this.setServedBy(user);
        return this;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return getId() != null && getId().equals(((Order) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", counter='" + getCounter() + "'" +
            ", bill=" + getBill() +
            ", paidId='" + getPaidId() + "'" +
            ", instruction='" + getInstruction() + "'" +
            "}";
    }
}
