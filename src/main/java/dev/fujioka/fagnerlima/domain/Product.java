package dev.fujioka.fagnerlima.domain;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.fujioka.fagnerlima.exception.ProductException;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dtCreation", "dtUpdate"},
        allowGetters = true)
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(length = 150)
    private String name;

    private String description;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dtCreation;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date dtUpdate;

    public Product() {
        super();
    }

    public Product(@NotEmpty String name, String description, @NotNull Integer quantity) {
        super();
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(Integer quantity) {
        if (this.quantity == null) {
            this.quantity = 0;
        }

        this.quantity += quantity;
    }

    public void subtractQuantity(Integer quantity) {
        if (this.quantity == null) {
            this.quantity = 0;
        }

        if (this.quantity < quantity) {
            throw new ProductException(String.format("Quantity unavailable. Only %d units of the Axon product %s.", this.quantity, name));
        }

        this.quantity -= quantity;
    }
}
