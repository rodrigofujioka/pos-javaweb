package dev.fujioka.eltonleite.presentation.dto.order;

import java.time.LocalDateTime;

public class OrderResponseTO {

    private Long id;

    private LocalDateTime dateOrder;

    private Long idUser;

    public OrderResponseTO(Long id, LocalDateTime dateOrder, Long idUser) {
        super();
        this.id = id;
        this.dateOrder = dateOrder;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public Long getIdUser() {
        return idUser;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderResponseTO other = (OrderResponseTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
