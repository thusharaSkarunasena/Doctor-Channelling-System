package lk.ijse.dcs.entity;

import javax.persistence.*;

@Entity
@Table(name = "drugPackDetail")
public class DrugPackDetails implements SuperEntity {

    @EmbeddedId
    private DrugPackDetails_FK drugPackDetails_fk;

    @ManyToOne
    @JoinColumn(name = "drugPackCode", referencedColumnName = "drugPackCode", insertable = false, updatable = false)
    private DrugPack drugPack_drugPackDetails;

    @ManyToOne
    @JoinColumn(name = "drugCode", referencedColumnName = "drugCode", insertable = false, updatable = false)
    private Drug drug_drugPackDetails;

    public DrugPackDetails() {
    }

    public DrugPackDetails(DrugPackDetails_FK drugPackDetails_fk) {
        this.drugPackDetails_fk = drugPackDetails_fk;
    }

    public DrugPackDetails_FK getDrugPackDetails_fk() {
        return drugPackDetails_fk;
    }

    public void setDrugPackDetails_fk(DrugPackDetails_FK drugPackDetails_fk) {
        this.drugPackDetails_fk = drugPackDetails_fk;
    }

    public DrugPack getDrugPack_drugPackDetails() {
        return drugPack_drugPackDetails;
    }

    public void setDrugPack_drugPackDetails(DrugPack drugPack_drugPackDetails) {
        this.drugPack_drugPackDetails = drugPack_drugPackDetails;
    }

    public Drug getDrug_drugPackDetails() {
        return drug_drugPackDetails;
    }

    public void setDrug_drugPackDetails(Drug drug_drugPackDetails) {
        this.drug_drugPackDetails = drug_drugPackDetails;
    }

    @Override
    public String toString() {
        return "DrugPackDetails{" +
                "drugPackDetails_fk=" + drugPackDetails_fk +
                '}';
    }
}
