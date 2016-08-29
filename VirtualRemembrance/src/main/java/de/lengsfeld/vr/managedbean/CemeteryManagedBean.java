package de.lengsfeld.vr.managedbean;

import de.lengsfeld.vr.model.Cemetery;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by robert on 26.08.16.
 */

public class CemeteryManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM Cemetery AS o";

    private Cemetery myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public CemeteryManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("VR");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public Cemetery getEntity() {
        return myEntity;
    }

    public void setEntity(Cemetery entity) {
        myEntity = entity;
    }

    // add new Cemetery
    public String create() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "cemeteryList";
    }

    // save edited Cemetery
    public String save() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            myEntity = entityManager.merge(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();
        return "cemeteryList";
    }

    // delete Cemetery
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Cemetery entity = getCurrentEntity();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "cemeteryList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<Cemetery> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<Cemetery>(entities));
    }

    public String startCreate() {
        myEntity = new Cemetery();
        return "createCemetery";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewCemetery";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editCemetery";
    }

    public Cemetery getCurrentEntity() {
        Cemetery entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public Cemetery getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        Cemetery entity = (Cemetery) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public Cemetery findEntity(Long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        Cemetery entity = entityManager.find(Cemetery.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<Cemetery> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (Cemetery entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<Cemetery> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<Cemetery> entities = (List<Cemetery>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }

    public String testThis() {
        System.out.println("TEST THIS");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VR");
        EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager

        String queryString = "SELECT c FROM Cemetery c";
        Query query = em.createQuery(queryString);

        List<Cemetery> cemeteries = query.getResultList();
        System.out.println(cemeteries);
        for (Cemetery c : cemeteries) {
            System.out.println(c.getName());
        }
        em.close();
        emf.close();
        return  "ok";
    }

}
