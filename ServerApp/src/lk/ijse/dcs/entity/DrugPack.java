package lk.ijse.dcs.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drugPack")
public class DrugPack implements SuperEntity{

    @Id
    @Column(name = "drugPackCode")
    private String drugPackCode;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "drugPack_drugPackDetails")
    private List<DrugPackDetails> drugPackDetails=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "employeeID", referencedColumnName = "employeeID")
    private Employee employee_drugPack;

    public DrugPack() {
    }

    public DrugPack(String drugPackCode, String name, Employee employee_drugPack) {
        this.drugPackCode = drugPackCode;
        this.name = name;
        this.employee_drugPack = employee_drugPack;
    }

    public String getDrugPackCode() {
        return drugPackCode;
    }

    public void setDrugPackCode(String drugPackCode) {
        this.drugPackCode = drugPackCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DrugPackDetails> getDrugPackDetails() {
        return drugPackDetails;
    }

    public void setDrugPackDetails(List<DrugPackDetails> drugPackDetails) {
        this.drugPackDetails = drugPackDetails;
    }

    public Employee getEmployee_drugPack() {
        return employee_drugPack;
    }

    public void setEmployee_drugPack(Employee employee_drugPack) {
        this.employee_drugPack = employee_drugPack;
    }

    @Override
    public String toString() {
        return "DrugPack{" +
                "drugPackCode='" + drugPackCode + '\'' +
                ", name='" + name + '\'' +
                ", employee_drugPack=" + employee_drugPack +
                '}';
    }
}
