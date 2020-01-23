package dev.fujioka.fagnerlima.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.fujioka.fagnerlima.domain.Client;
import dev.fujioka.fagnerlima.domain.Store;
import dev.fujioka.fagnerlima.domain.User;

public class SaleResponseTO implements Serializable {

    private static final long serialVersionUID = 6836650014039557230L;

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private Store store;

    private User user;

    private Client client;

    private Set<SaleItemResponseTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<SaleItemResponseTO> getItems() {
        return items;
    }

    public void setItems(Set<SaleItemResponseTO> items) {
        this.items = items;
    }
}
