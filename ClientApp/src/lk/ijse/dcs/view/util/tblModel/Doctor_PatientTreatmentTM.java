package lk.ijse.dcs.view.util.tblModel;

public class Doctor_PatientTreatmentTM {

    private String packOrDrugCode;
    private String description;

    public Doctor_PatientTreatmentTM() {
    }

    public Doctor_PatientTreatmentTM(String packOrDrugCode, String description) {
        this.packOrDrugCode = packOrDrugCode;
        this.description = description;
    }

    public String getPackOrDrugCode() {
        return packOrDrugCode;
    }

    public void setPackOrDrugCode(String packOrDrugCode) {
        this.packOrDrugCode = packOrDrugCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Doctor_PatientTreatmentTM{" +
                "packOrDrugCode='" + packOrDrugCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

