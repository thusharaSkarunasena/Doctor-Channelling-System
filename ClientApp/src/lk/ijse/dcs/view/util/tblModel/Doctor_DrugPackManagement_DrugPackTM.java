package lk.ijse.dcs.view.util.tblModel;

public class Doctor_DrugPackManagement_DrugPackTM {

    private String drugPackCode;
    private String drugPackName;

    public Doctor_DrugPackManagement_DrugPackTM() {
    }

    public Doctor_DrugPackManagement_DrugPackTM(String drugPackCode, String drugPackName) {
        this.drugPackCode = drugPackCode;
        this.drugPackName = drugPackName;
    }

    public String getDrugPackCode() {
        return drugPackCode;
    }

    public void setDrugPackCode(String drugPackCode) {
        this.drugPackCode = drugPackCode;
    }

    public String getDrugPackName() {
        return drugPackName;
    }

    public void setDrugPackName(String drugPackName) {
        this.drugPackName = drugPackName;
    }

    @Override
    public String toString() {
        return "Doctor_DrugPackManagement_DrugPackTM{" +
                "drugPackCode='" + drugPackCode + '\'' +
                ", drugPackName='" + drugPackName + '\'' +
                '}';
    }
}
