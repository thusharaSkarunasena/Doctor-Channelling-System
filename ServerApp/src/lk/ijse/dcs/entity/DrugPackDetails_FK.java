package lk.ijse.dcs.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DrugPackDetails_FK implements Serializable {

    private String drugPackCode;
    private String drugCode;

    public DrugPackDetails_FK() {
    }

    public DrugPackDetails_FK(String drugPackCode, String drugCode) {
        this.drugPackCode = drugPackCode;
        this.drugCode = drugCode;
    }

    public String getDrugPackCode() {
        return drugPackCode;
    }

    public void setDrugPackCode(String drugPackCode) {
        this.drugPackCode = drugPackCode;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    @Override
    public String toString() {
        return "DrugPackDetails_FK{" +
                "drugPackCode='" + drugPackCode + '\'' +
                ", drugCode='" + drugCode + '\'' +
                '}';
    }
}
