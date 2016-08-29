package de.lengsfeld.vr.ejb;

import de.lengsfeld.vr.model.Cemetery;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by robert on 27.08.16.
 */
@Stateless
public class TestEJB {

    private List<Cemetery> cemeteries;

    @PersistenceContext(unitName = "VR")
    private EntityManager em;

    @PostConstruct
    public void makeList() {
//        String queryString = "SELECT c FROM Cemetery c";
//        Query query = em.createQuery(queryString);
//        cemeteries = query.getResultList();

        TypedQuery<Cemetery> query = em.createNamedQuery(Cemetery.findAll, Cemetery.class);
        cemeteries = query.getResultList();

        System.out.println("TestEJB query result: " + cemeteries);
        for (Cemetery c : cemeteries) {
            System.out.println(c.getName());
        }
    }

    @Produces
    @Named
    public List<Cemetery> getCemeteriesTest() {
        TypedQuery<Cemetery> query = em.createNamedQuery(Cemetery.findAll, Cemetery.class);
        cemeteries = query.getResultList();

        System.out.println("TestEJB query result: " + cemeteries);
        for (Cemetery c : cemeteries) {
            System.out.println(c.getName());
        }
        return cemeteries;
    }

}
