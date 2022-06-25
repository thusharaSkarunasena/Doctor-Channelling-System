package lk.ijse.dcs.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AppointmentDetails_FK implements Serializable {

    private String appointmentNO;
    private String drugCode;

    public AppointmentDetails_FK() {
    }

    public AppointmentDetails_FK(String appointmentNO, String drugCode) {
        this.appointmentNO = appointmentNO;
        this.drugCode = drugCode;
    }

    public String getAppointmentNO() {
        return appointmentNO;
    }

    public void setAppointmentNO(String appointmentNO) {
        this.appointmentNO = appointmentNO;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    @Override
    public String toString() {
        return "AppointmentDetails_FK{" +
                "appointmentNO='" + appointmentNO + '\'' +
                ", drugCode='" + drugCode + '\'' +
                '}';
    }
}
