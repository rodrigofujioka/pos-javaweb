package dev.fujioka.fagnerlima.web.dto;

import java.io.Serializable;
import java.util.Set;

public class SaleRequestTO implements Serializable {

    private static final long serialVersionUID = 6836650014039557230L;

    private Long id;

    private Long storeId;

    private Long userId;

    private Long clientId;

    private Set<SaleItemRequestTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Set<SaleItemRequestTO> getItems() {
        return items;
    }

    public void setItems(Set<SaleItemRequestTO> items) {
        this.items = items;
    }
}
