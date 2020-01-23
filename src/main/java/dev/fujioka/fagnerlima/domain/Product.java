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
    private Integer amount;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dtCreation;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date dtUpdate;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void addAmount(Integer amount) {
        if (this.amount == null) {
            this.amount = 0;
        }

        this.amount += amount;
    }

    public void subtractAmount(Integer amount) {
        if (this.amount == null) {
            this.amount = 0;
        }

        if (this.amount < amount) {
            throw new RuntimeException("Quantidade indisponível");
        }

        this.amount -= amount;
    }
}
