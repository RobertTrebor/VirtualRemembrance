package de.lengsfeld.vr.services;

import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Country;
import de.lengsfeld.vr.model.Grave;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

@Stateless
@Alternative
public class MockCemeteryServiceBean implements CemeteryService {

    @Override
    public List<Cemetery> getAllCemeteries() {
        
        System.out.println("HERE WE GO");
        Country country = new Country("AA", "AAA GREAT COUNTRY");
        Cemetery cemetery = new Cemetery("Dorotheenstädtischer-Friedrichswerderscher Friedh", "DE");
        System.out.println(cemetery);
        cemetery.setCity("Berlin");
        cemetery.setZipcode("10115");
        cemetery.setStreet("Chausseestr. 126");
        cemetery.setLatitude(52.52778);
        cemetery.setLongitude(13.384167);
        
        Cemetery cemetery2 = new Cemetery("Second Friedhof", "DE");
        System.out.println(cemetery2);
        cemetery2.setCity("Berlin");
        cemetery2.setZipcode("10999");
        cemetery2.setStreet("Sstr. 126");
        cemetery2.setLatitude(52.52778);
        cemetery2.setLongitude(13.384167);
        
        Grave grave = new Grave("Robert", "Lengsfeld", "m", cemetery);
        Grave grave2 = new Grave("Sombody", "IsDead", "f", cemetery);

        // 2-Obtains an entity manager and a transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.lengsfeld.virtualremembrance_VirtualRemembrance5_jar_1.0PU");
        EntityManager em = emf.createEntityManager();
        
        // 3-Persists the book to the database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(country);
        em.persist(cemetery);
        em.persist(cemetery2);
        em.persist(grave);
        em.persist(grave2);
        tx.commit();

        // 4-Executes the named query
        country = em.createNamedQuery("find", Country.class).getSingleResult();
        System.out.println("######### " + country.getCountryname());
        cemetery = em.createNamedQuery("Cemetery.findc", Cemetery.class).getSingleResult();
        System.out.println("$$$$$$$$$ " + cemetery.getName());
        grave = em.createNamedQuery("Grave.findg", Grave.class).getSingleResult();
        System.out.println("&&&&&&&&&& " + grave.getFirstname());
        
        // 5-Closes the entity manager and the factory
        em.close();
        emf.close();
        
        // Temporary Stuff
        List<Cemetery> cemeteryList = new LinkedList<>();
        cemeteryList.add(cemetery);
        cemeteryList.add(cemetery2);

        return cemeteryList;
    }

    @Override
    public void addCemetery(Cemetery cemetery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCemetery(Cemetery cemetery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCemetery(Cemetery cemetery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
