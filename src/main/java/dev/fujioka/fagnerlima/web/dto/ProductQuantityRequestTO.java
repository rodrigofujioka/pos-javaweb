package dev.fujioka.fagnerlima.web.dto;

import java.io.Serializable;

public class ProductQuantityRequestTO implements Serializable {

    private static final long serialVersionUID = 6207327148552574937L;

    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
