import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

/**
 * Created by Simona Simeonova on 11/2/2017.
 */
public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory =
                cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student example = new Student();
        example.setName("Simona");
        example.setBirthDate(new Date());

        // Your Code Here
        session.getTransaction().commit();
        session.close();

    }
}
