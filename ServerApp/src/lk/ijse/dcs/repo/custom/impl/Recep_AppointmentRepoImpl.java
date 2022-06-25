package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.repo.CrudRepositoryImpl;
import lk.ijse.dcs.repo.custom.Recep_AppointmentRepo;
import org.hibernate.Session;

import java.util.ArrayList;

public class Recep_AppointmentRepoImpl extends CrudRepositoryImpl<Appointment, String> implements Recep_AppointmentRepo {

    private Session session;

    @Override
    public void setMySession(Session mySession) throws Exception {
        this.session=mySession;
    }

    @Override
    public String generateNextAppointmentNumber() throws Exception {
        String lastAppointmentNO = null;
        Integer NpartDCount = 0;
        ArrayList<String> ids = (ArrayList<String>) session.createNativeQuery("select appointmentNO from Appointment " +
                "order by 1 desc limit 1").list();

        for (String lastID : ids) {
            lastAppointmentNO = lastID;
        }
        session.getTransaction().commit();

        if (lastAppointmentNO != null) {

            String[] output = lastAppointmentNO.split("-");

            Integer Npart = Integer.parseInt(output[1]);
            Npart = Npart + 1;
            Integer testNpart = Npart;


            while (testNpart != 0) {
                testNpart = testNpart / 10;
                NpartDCount++;
            }

            String nextAppointmentNO = "APNMT-";

            Integer rounds = 8 - NpartDCount;

            while (rounds != 0) {
                nextAppointmentNO = nextAppointmentNO + "0";
                rounds--;
            }

            nextAppointmentNO = nextAppointmentNO + "" + Npart;

            return nextAppointmentNO;
        } else {
            return "APNMT-00000001";
        }
    }

    @Override
    public String getNextQueueNumber(String date, String doctorID) throws Exception {
        String lastQueueNO = null;
        Integer NpartDCount = 0;
        String sql="select queueNumber from Appointment where appointmentDate='"+date+"' and doctorsID='"+doctorID+"' order by 1 desc limit 1";
        ArrayList<String> ids = (ArrayList<String>) session.createNativeQuery(sql).list();

        for (String lastID : ids) {
            lastQueueNO = lastID;
        }
        session.getTransaction().commit();

        if (lastQueueNO != null) {

            String[] output = lastQueueNO.split("-");

            Integer Npart = Integer.parseInt(output[1]);
            Npart = Npart + 1;
            Integer testNpart = Npart;


            while (testNpart != 0) {
                testNpart = testNpart / 10;
                NpartDCount++;
            }

            String nextQueueNO = "Q-";

            Integer rounds = 3 - NpartDCount;

            while (rounds != 0) {
                nextQueueNO = nextQueueNO + "0";
                rounds--;
            }

            nextQueueNO = nextQueueNO + "" + Npart;

            return nextQueueNO;
        } else {
            return "Q-001";
        }
    }

}
