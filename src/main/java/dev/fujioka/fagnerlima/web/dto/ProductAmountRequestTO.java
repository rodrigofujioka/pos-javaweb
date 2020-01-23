package dev.fujioka.fagnerlima.web.dto;

import java.io.Serializable;

public class ProductAmountRequestTO implements Serializable {

    private static final long serialVersionUID = 6207327148552574937L;

    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
