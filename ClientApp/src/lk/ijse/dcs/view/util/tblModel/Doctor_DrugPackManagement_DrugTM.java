package lk.ijse.dcs.view.util.tblModel;

public class Doctor_DrugPackManagement_DrugTM {

    private String drugCode;
    private String drugName;
    private String description;

    public Doctor_DrugPackManagement_DrugTM() {
    }

    public Doctor_DrugPackManagement_DrugTM(String drugCode, String drugName, String description) {
        this.drugCode = drugCode;
        this.drugName = drugName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Doctor_DrugPackManagement_DrugTM{" +
                "drugCode='" + drugCode + '\'' +
                ", drugName='" + drugName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
