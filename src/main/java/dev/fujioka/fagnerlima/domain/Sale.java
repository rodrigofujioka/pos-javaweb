package dev.fujioka.fagnerlima.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Sale implements Serializable {

    private static final long serialVersionUID = -8723500709354433990L;

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Store store;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    @OneToMany(mappedBy = "sale")
    private Set<SaleItem> items;

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

    public Set<SaleItem> getItems() {
        return items;
    }

    public void setItems(Set<SaleItem> items) {
        this.items = items;
    }
}
