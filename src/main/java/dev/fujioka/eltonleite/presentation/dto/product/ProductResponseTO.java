package dev.fujioka.eltonleite.presentation.dto.product;

public class ProductResponseTO {
    private Long id;
    private String name;
    private String description;
    private Integer manufactureYear;

    public ProductResponseTO(Long id, String name, String description, Integer manufactureYear) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.manufactureYear = manufactureYear;
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

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

}
