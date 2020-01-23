package dev.fujioka.fagnerlima.web.dto;

import java.io.Serializable;

public class SaleItemRequestTO implements Serializable {

    private static final long serialVersionUID = -1370364974642129884L;

    private Long id;

    private Long productId;

    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
