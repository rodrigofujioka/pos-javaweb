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

}
