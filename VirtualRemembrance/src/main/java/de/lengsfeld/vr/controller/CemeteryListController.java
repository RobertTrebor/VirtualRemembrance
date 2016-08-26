package de.lengsfeld.vr.controller;

import de.lengsfeld.vr.controller.CemeteryEditController.Mode;
import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.util.Events.Deleted;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CemeteryListController implements Serializable {
    @Inject                                 //This is just for debug code TestThis
    private EntityManager em;               //  " "

    @Inject
    private CemeteryEditController cemeteryEditController;
    
    @Inject
    private GraveListController graveListController;
    
    @Inject
    @Deleted
    private Event<Cemetery> cemeteryDeleteEventSrc;
        
    private Cemetery cemeteryToDelete;

    public String doAddCemetery() {
        System.out.println("CemeteryListController - doAddCemetery");
        cemeteryEditController.setCemeteryToEdit(Mode.ADD);
        return Pages.CEMETERY_EDIT;
    }

    public String doEditCemetery(Cemetery cemetery) {
        System.out.println("CemeteryListController - doEditCemetery");
        cemeteryEditController.setCemeteryToEdit(Mode.EDIT, cemetery);
        return Pages.CEMETERY_EDIT;
    }

    public String doListGraves(Cemetery cemetery) {
        System.out.println("CemeteryListController - doListGraves");
        graveListController.setCemetery(cemetery);
        return Pages.GRAVE_LIST;
    }
    
    public void doDeleteCemetery(Cemetery cemetery) {
        System.out.println("CemeteryListController - doDeleteCemetery");
        this.cemeteryToDelete = cemetery;
        System.out.println(cemeteryToDelete);
        commitDeleteCemetery();  //WITHOUT THIS LINE, IT WON'T WORK!
    }

    public void commitDeleteCemetery() {
        System.out.println("CemeteryListController - commitDeleteCemetery");
        cemeteryDeleteEventSrc.fire(cemeteryToDelete);
    }


    public void testThis() {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("VR");
        //EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager
        String queryString = "SELECT c FROM Cemetery c";
        System.out.println(queryString);
        Query query = em.createQuery(queryString);

        List<Cemetery> cemeteries = query.getResultList();
        System.out.println(cemeteries);
        for (Cemetery c : cemeteries) {
            System.out.println(c.getName());
        }
        em.close();
        //emf.close();
    }
}
