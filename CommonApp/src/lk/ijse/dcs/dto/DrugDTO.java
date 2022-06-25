package lk.ijse.dcs.dto;

public class DrugDTO implements SuperDTO {

    private String drugCode;
    private String name;
    private String brand;
    private String description;
    private double unitPrice;

    public DrugDTO() {
    }

    public DrugDTO(String drugCode, String name, String brand, String description, double unitPrice) {
        this.drugCode = drugCode;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "DrugDTO{" +
                "drugCode='" + drugCode + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
