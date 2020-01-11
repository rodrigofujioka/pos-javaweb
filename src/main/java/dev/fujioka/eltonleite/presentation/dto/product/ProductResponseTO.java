package dev.fujioka.eltonleite.presentation.dto.product;

public class ProductResponseTO {
    private Long id;
    private String name;
    private String description;
    private Integer yearManufacture;

    public ProductResponseTO(Long id, String name, String description, Integer yearManufacture) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearManufacture = yearManufacture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearManufacture() {
        return yearManufacture;
    }

    public void setYearManufacture(Integer yearManufacture) {
        this.yearManufacture = yearManufacture;
    }

}
