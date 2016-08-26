package de.lengsfeld.vr.managedbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.lengsfeld.vr.model.Grave;

/**
 * Created by robert on 26.08.16.
 */
public class GraveManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM Grave AS o";

    private Grave myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public GraveManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("VR");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public Grave getEntity() {
        return myEntity;
    }

    public void setEntity(Grave entity) {
        myEntity = entity;
    }

    // add new Grave
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

        return "graveList";
    }

    // save edited Grave
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
        return "graveList";
    }

    // delete Grave
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Grave entity = getCurrentEntity();
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

        return "graveList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<Grave> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<Grave>(entities));
    }

    public String startCreate() {
        myEntity = new Grave();
        return "createGrave";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewGrave";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editGrave";
    }

    public Grave getCurrentEntity() {
        Grave entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public Grave getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        Grave entity = (Grave) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public Grave findEntity(Long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        Grave entity = entityManager.find(Grave.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<Grave> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (Grave entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<Grave> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<Grave> entities = (List<Grave>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
