package lk.ijse.dcs.dto;

import java.util.List;

public class DrugPackDTO implements SuperDTO {

    private String drugPackCode;
    private String name;
    private String employeeID;
    private List<DrugPackDetailsDTO> drugPackDetails;

    public DrugPackDTO() {
    }

    public DrugPackDTO(String drugPackCode, String name) {
        this.drugPackCode = drugPackCode;
        this.name = name;
    }

    public DrugPackDTO(String drugPackCode, String name, String employeeID, List<DrugPackDetailsDTO> drugPackDetails) {
        this.drugPackCode = drugPackCode;
        this.name = name;
        this.employeeID = employeeID;
        this.drugPackDetails = drugPackDetails;
    }

    public String getDrugPackCode() {
        return drugPackCode;
    }

    public void setDrugPackCode(String drugPackCode) {
        this.drugPackCode = drugPackCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public List<DrugPackDetailsDTO> getDrugPackDetails() {
        return drugPackDetails;
    }

    public void setDrugPackDetails(List<DrugPackDetailsDTO> drugPackDetails) {
        this.drugPackDetails = drugPackDetails;
    }

    @Override
    public String toString() {
        return "DrugPackDTO{" +
                "drugPackCode='" + drugPackCode + '\'' +
                ", name='" + name + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", drugPackDetails=" + drugPackDetails +
                '}';
    }
}
