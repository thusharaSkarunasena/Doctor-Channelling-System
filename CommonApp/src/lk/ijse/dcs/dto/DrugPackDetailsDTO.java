package lk.ijse.dcs.dto;

public class DrugPackDetailsDTO implements SuperDTO {

    private String drugPackCode;
    private String drugCode;

    public DrugPackDetailsDTO() {
    }

    public DrugPackDetailsDTO(String drugPackCode, String drugCode) {
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
        return "DrugPackDetailsDTO{" +
                "drugPackCode='" + drugPackCode + '\'' +
                ", drugCode='" + drugCode + '\'' +
                '}';
    }
}
