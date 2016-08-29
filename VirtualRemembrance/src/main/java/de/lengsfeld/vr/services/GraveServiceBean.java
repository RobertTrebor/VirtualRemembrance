package de.lengsfeld.vr.services;

import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Grave;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class GraveServiceBean implements GraveService, Serializable {

    @PersistenceContext(unitName="VR")
    EntityManager entityManager;
    
    @Override
    public List<Grave> getGraveList(Cemetery cemetery) {

        System.out.println("GraveServiceBean - getGraveList from: " + cemetery.getName());
        Cemetery managedCemetery = entityManager.find(Cemetery.class, cemetery.getId());
        System.out.println("managedCemetery: " + managedCemetery);

        //System.out.println("TypedQuery: ");
        //TypedQuery<Grave> query;
        //query = entityManager.createNamedQuery(Grave.findAll, Grave.class);
        //query.setParameter("cemetery", managedCemetery);
        //TypedQuery<Cemetery> query = entityManager.createNamedQuery(Cemetery.findById, Cemetery.class);
        //cmetery = query.getResultList();
        List<Grave> graves = managedCemetery.getGraves();
        System.out.println("List<Grave> graves: " + graves);
        graves.size();
        return graves;
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