package lk.ijse.dcs.view.util.tblModel;

public class Doctor_PreviousTreatment_AppointmentDetailTM {

    private String drugCode;
    private String description;

    public Doctor_PreviousTreatment_AppointmentDetailTM() {
    }

    public Doctor_PreviousTreatment_AppointmentDetailTM(String drugCode, String description) {
        this.drugCode = drugCode;
        this.description = description;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Doctor_PreviousTreatment_AppointmentDetailTM{" +
                "drugCode='" + drugCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
