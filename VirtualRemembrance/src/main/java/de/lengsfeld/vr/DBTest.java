package de.lengsfeld.vr;

import de.lengsfeld.vr.model.Cemetery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by robert on 24.08.16.
 */


public class DBTest {

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");


    public static void main(String[] args) {


        // Use persistence.xml configuration
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VRTest");
        EntityManager entityManager = emf.createEntityManager(); // Retrieve an application managed entity manager

            TypedQuery<Cemetery> query = entityManager.createNamedQuery(Cemetery.findAll, Cemetery.class);
            List<Cemetery> cemeteries = query.getResultList();
            System.out.println(cemeteries);

        entityManager.close();

        emf.close(); //close at application end
        }
}
