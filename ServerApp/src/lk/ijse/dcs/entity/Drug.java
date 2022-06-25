package lk.ijse.dcs.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drug")
public class Drug implements SuperEntity{

    @Id
    @Column(name = "drugCode")
    private String drugCode;
    @Column(name = "name")
    private String name;
    @Column(name = "brand")
    private String brand;
    @Column(name = "description")
    private String description;
    @Column(name = "unitPrice")
    private double unitPrice;

    @OneToMany(mappedBy = "drug_appointmentDetails")
    private List<AppointmentDetails> appointmentDetails=new ArrayList<>();

    @OneToMany(mappedBy = "drug_drugPackDetails")
    private List<DrugPackDetails> drugPackDetails=new ArrayList<>();

    public Drug() {
    }

    public Drug(String drugCode, String name, String brand, String description, double unitPrice) {
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
        return "Drug{" +
                "drugCode='" + drugCode + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
