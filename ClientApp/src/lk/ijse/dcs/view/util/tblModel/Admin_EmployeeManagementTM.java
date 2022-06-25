package lk.ijse.dcs.view.util.tblModel;

public class Admin_EmployeeManagementTM {

    private String employeeID;
    private String name;
    private String employment;
    private String nic;

    public Admin_EmployeeManagementTM() {
    }

    public Admin_EmployeeManagementTM(String employeeID, String name, String employment, String nic) {
        this.employeeID = employeeID;
        this.name = name;
        this.employment = employment;
        this.nic = nic;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Admin_EmployeeManagementTM{" +
                "employeeID='" + employeeID + '\'' +
                ", name='" + name + '\'' +
                ", employment='" + employment + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
