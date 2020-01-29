package dev.fujioka.fagnerlima.web.dto;

import java.io.Serializable;

import dev.fujioka.fagnerlima.domain.Product;

public class SaleItemResponseTO implements Serializable {

    private static final long serialVersionUID = 3715510522906649762L;

    private Long id;

    private Product product;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
