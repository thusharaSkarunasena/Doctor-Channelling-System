package lk.ijse.dcs.view.util.tblModel;

public class Pharm_DrugManagementTM {

    private String drugCode;
    private String name;
    private String description;
    private String unitPrice;

    public Pharm_DrugManagementTM() {
    }

    public Pharm_DrugManagementTM(String drugCode, String name, String description, String unitPrice) {
        this.drugCode = drugCode;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Pharm_DrugManagementTM{" +
                "drugCode='" + drugCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }
}
