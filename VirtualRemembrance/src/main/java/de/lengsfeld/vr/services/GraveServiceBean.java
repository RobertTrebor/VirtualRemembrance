package de.lengsfeld.vr.services;

import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Grave;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GraveServiceBean implements GraveService, Serializable {

    @PersistenceContext(unitName="VR")
    private EntityManager entityManager;
    
    @Override
    public List<Grave> getGraveList(Cemetery cemetery) {

        System.out.println("GraveServiceBean - getGraveList from: " + cemetery.getName());
        Cemetery managedCemetery = entityManager.find(Cemetery.class, cemetery.getId());
        System.out.println("managedCemetery: " + managedCemetery);

        //System.out.println("TypedQuery: ");
        Query query = entityManager.createNamedQuery(Grave.findByCemetery).setParameter("cemetery", managedCemetery);


        //query.setParameter("cemetery", managedCemetery);
        //TypedQuery<Cemetery> query = entityManager.createNamedQuery(Cemetery.findById, Cemetery.class);
        //cmetery = query.getResultList();
        List<Grave> graves = query.getResultList();
        System.out.println("List<Grave> graves: " + graves);
        graves.size();
        return graves;
    }

    public Grave findGrave(Grave grave){
        return entityManager.find(Grave.class, grave.getId());
    }

    @Override
    public void addGrave(Grave grave) {
        System.out.println("GraveServiceBean.java - addGrave: grave: " + grave);
        //grave.setSex("M");
        entityManager.persist(grave);
    }

    @Override
    public void deleteGrave(Grave grave) {
        System.out.println("GraveServiceBean.java - deleteGrave: grave" + grave);
        Grave managedGrave = entityManager.find(Grave.class, grave.getId());
        entityManager.remove(managedGrave);
    }

    @Override
    public void updateGrave(Grave grave) {
        System.out.println("GraveServiceBean.java - updateGrave: grave" + grave);
        entityManager.merge(grave);
    }

}