package dev.fujioka.eltonleite.presentation.assembler;

import java.util.List;
import java.util.stream.Collectors;

import dev.fujioka.eltonleite.domain.model.warehouse.Warehouse;
import dev.fujioka.eltonleite.presentation.dto.warehouse.WarehouseRequestTO;
import dev.fujioka.eltonleite.presentation.dto.warehouse.WarehouseResponseTO;

public final class WarehouseAssembler {
    
    private WarehouseAssembler() {
    }
    
    public static Warehouse from(WarehouseRequestTO requestTO) {
        return new Warehouse(requestTO.getName(), requestTO.getAddress());
    }
    
    public static WarehouseResponseTO from(Warehouse warehouse) {
        return new WarehouseResponseTO(warehouse.getId(), warehouse.getName(), warehouse.getAddress());
    }
    
    public static List<WarehouseResponseTO> from(List<Warehouse> warehouses) {
        return warehouses.stream().map(WarehouseAssembler::from).collect(Collectors.toList());
    }

}
