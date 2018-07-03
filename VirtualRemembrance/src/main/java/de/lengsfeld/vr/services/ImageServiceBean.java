package de.lengsfeld.vr.services;

import de.lengsfeld.vr.model.Image;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
public class ImageServiceBean implements Serializable {

    @PersistenceContext(unitName="VR")
    EntityManager entityManager;
    
    public void uploadImage(Image image){
        System.out.println("ImageServiceBean.java - uploadImage: image" + image);
        entityManager.persist(image);
    }

}