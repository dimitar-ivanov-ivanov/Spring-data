import entities.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by Simona Simeonova on 11/3/2017.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        User found = entityManager.find(User.class,1);
        found.setNotes("New notes");
        entityManager.persist(found);

        /*entityManager.detach(user);
        user.setDescription("some new description");
        entityManager.merge(user);*/

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
