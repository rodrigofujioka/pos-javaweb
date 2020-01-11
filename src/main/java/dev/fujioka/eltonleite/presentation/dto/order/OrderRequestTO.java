package dev.fujioka.eltonleite.presentation.dto.order;

import java.time.LocalDateTime;

public class OrderRequestTO {

    private LocalDateTime dateOrder;

    private Long idUser;

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

}
