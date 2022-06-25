package lk.ijse.dcs.view.util.tblModel;

public class Doctor_DrugPackManagement_DrugPackDetailTM {

    private String drugCode;
    private String drugName;

    public Doctor_DrugPackManagement_DrugPackDetailTM() {
    }

    public Doctor_DrugPackManagement_DrugPackDetailTM(String drugCode, String drugName) {
        this.drugCode = drugCode;
        this.drugName = drugName;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    @Override
    public String toString() {
        return "Doctor_DrugPackManagement_DrugPackDetailTM{" +
                "drugCode='" + drugCode + '\'' +
                ", drugName='" + drugName + '\'' +
                '}';
    }
}
