package lk.ijse.dcs.view.util.tblModel;

public class Pharm_IssueDrugsTM {

    private String drugCode;
    private String description;
    private String qty;

    public Pharm_IssueDrugsTM() {
    }

    public Pharm_IssueDrugsTM(String drugCode, String description, String qty) {
        this.drugCode = drugCode;
        this.description = description;
        this.qty = qty;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Pharm_IssueDrugsTM{" +
                "drugCode='" + drugCode + '\'' +
                ", description='" + description + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
