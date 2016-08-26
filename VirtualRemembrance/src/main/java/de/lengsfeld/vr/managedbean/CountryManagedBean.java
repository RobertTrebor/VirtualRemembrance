package de.lengsfeld.vr.managedbean;

import de.lengsfeld.vr.model.Country;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by robert on 26.08.16.
 */
public class CountryManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM Country AS o";

    private Country myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public CountryManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("VR");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public Country getEntity() {
        return myEntity;
    }

    public void setEntity(Country entity) {
        myEntity = entity;
    }

    // add new Country
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

        return "countryList";
    }

    // save edited Country
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
        return "countryList";
    }

    // delete Country
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Country entity = getCurrentEntity();
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

        return "countryList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<Country> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<Country>(entities));
    }

    public String startCreate() {
        myEntity = new Country();
        return "createCountry";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewCountry";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editCountry";
    }

    public Country getCurrentEntity() {
        Country entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public Country getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        Country entity = (Country) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public Country findEntity(String id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        Country entity = entityManager.find(Country.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<Country> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (Country entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<Country> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<Country> entities = (List<Country>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
