package de.lengsfeld.vr;

import de.lengsfeld.vr.model.Cemetery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by robert on 23.08.16.
 */
public class Main {
    public static void main(String[] args) {


        // Use persistence.xml configuration

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VR2");
        EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager

        String queryString = "SELECT c FROM Cemetery c";
        Query query = em.createQuery(queryString);


        List<Cemetery> cemeteries = query.getResultList();
        System.out.println(cemeteries);
        for (Cemetery c : cemeteries) {
            System.out.println(c.getName());
        }

        em.close();

        emf.close(); //close at application end
    }

}
