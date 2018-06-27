package de.lengsfeld.vr.ejb;

import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Grave;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 28.08.16.
 */

@Singleton
@Startup
public class StartEJB {

    @PersistenceContext(unitName = "VR")
    EntityManager entityManager;

    public StartEJB() {
    }

    public void getAllCemeteries() {
        TypedQuery<Cemetery> query = entityManager.createNamedQuery(Cemetery.findAll, Cemetery.class);
        List<Cemetery> cemeteries = query.getResultList();
        System.out.println("StartEJB.java:  getAllCemeteries): " + cemeteries);
        if(cemeteries.isEmpty()) cemeteries = createSampleData();
    }

    private List<Cemetery> createSampleData() {
        System.out.println("HERE WE GO");
//        Country country = new Country("AA", "AAA GREAT COUNTRY");
        Cemetery cemetery = new Cemetery("Dorotheenstädtischer-Friedrichswerderscher Friedh", "DE");
        System.out.println(cemetery);
        cemetery.setCity("Berlin");
        cemetery.setZipcode("10115");
        cemetery.setStreet("Chausseestr. 126");
        cemetery.setLatitude("52.52778");
        cemetery.setLongitude("13.384167");

        Cemetery cemetery2 = new Cemetery("Second Friedhof", "DE");
        System.out.println(cemetery2);
        cemetery2.setCity("Berlin");
        cemetery2.setZipcode("10999");
        cemetery2.setStreet("Sstr. 126");
        cemetery2.setLatitude("52.52778");
        cemetery2.setLongitude("13.384167");
        System.out.println("DO WE GET To before Grave?");
        Grave grave = new Grave("Robert", "Lengsfeld", "m", cemetery);
        Grave grave2 = new Grave("Sombody", "IsDead", "f", cemetery);
        System.out.println("DO WE GET to after grave?");
        // 2-Obtains an entity manager and a transaction
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.lengsfeld.virtualremembrance_VirtualRemembrance5_jar_1.0PU");
        //EntityManager em = emf.createEntityManager();
        // 3-Persists the book to the database
        System.out.println("DO WE GET HERE?");
//        EntityTransaction tx = entityManager.getTransaction();
        System.out.println("DO WE GET HERE?");
//        tx.begin();
        System.out.println("DO WE GET HERE?");
//        entityManager.persist(country);
        entityManager.persist(cemetery);
        entityManager.persist(cemetery2);
        entityManager.persist(grave);
        entityManager.persist(grave2);
        System.out.println("DO WE GET to before COMMIT?");
//        tx.commit();

        // 4-Executes the named query
//        country = entityManager.createNamedQuery("find", Country.class).getSingleResult();
//        System.out.println("######### " + country.getCountryname());
//        cemetery = entityManager.createNamedQuery("Cemetery.findc", Cemetery.class).getSingleResult();
        System.out.println("$$$$$$$$$ " + cemetery.getName());
        grave = entityManager.createNamedQuery("Grave.findg", Grave.class).getSingleResult();
        System.out.println("&&&&&&&&&& " + grave.getFirstname());

        // 5-Closes the entity manager and the factory
        //em.close();
        //emf.close();
        // Temporary Stuff
        List<Cemetery> cemeteryList = new ArrayList<>();
        cemeteryList.add(cemetery);
        cemeteryList.add(cemetery2); //To change body of generated methods, choose Tools | Templates.
        System.out.println(cemeteryList.get(0).getName());
        cemeteryList = entityManager.createNamedQuery("Cemetery.findAll", Cemetery.class).getResultList();
        System.out.println("After creating sample data, does Grave contain values?");
        System.out.println(cemeteryList.get(1).getName());
        System.out.println(cemeteryList.get(0).getGraves());
        return cemeteryList;
    }

}
