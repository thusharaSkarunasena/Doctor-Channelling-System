package lk.ijse.dcs.resources;

import lk.ijse.dcs.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();

        Metadata metadata = new MetadataSources(registry)

                .addAnnotatedClass(Appointment.class)
                .addAnnotatedClass(AppointmentDetails.class)
                .addAnnotatedClass(Drug.class)
                .addAnnotatedClass(DrugPack.class)
                .addAnnotatedClass(DrugPackDetails.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Payment.class)
                .buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
