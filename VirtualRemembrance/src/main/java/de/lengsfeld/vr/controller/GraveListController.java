package de.lengsfeld.vr.controller;

import de.lengsfeld.vr.controller.GraveEditController.Mode;
import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Grave;
import de.lengsfeld.vr.services.GraveService;
import de.lengsfeld.vr.services.ImageServiceBean;
import de.lengsfeld.vr.util.Events.Deleted;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class GraveListController implements Serializable {

    private List<Grave> graves;
    
    @Inject
    private GraveService graveService;
    
    @Inject
    private GraveEditController graveEditController;

    @Inject
    private ImageServiceBean imageServiceBean;

    @Inject
    @Deleted
    private Event<Grave> graveDeleteEventSrc;

    private Cemetery cemetery;

    private Grave graveToDelete;

    public Cemetery getCemetery() {
        System.out.print("GraveListController - getCemetery: ");
        if (cemetery != null) {
            System.out.println(cemetery);
        }
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        System.out.println("GraveListController - setCemetery: " + cemetery);
        //cemetery.setGraves(graveService.getGraveList(cemetery.getId()));
        this.cemetery = cemetery;
    }

    public String doAddGrave() {
        System.out.println("GraveListController.java - doAddGrave");
        graveEditController.setGraveToEdit(Mode.ADD, cemetery);
        return Pages.GRAVE_EDIT;
    }
    
    public String doEditGrave(Grave grave) {
        System.out.println("GraveListController.java - doEditGrave");
        graveEditController.setGraveToEdit(Mode.EDIT, grave, cemetery);
        return Pages.GRAVE_EDIT;
    }

    public void doDeleteGrave(Grave grave) {
        System.out.println("GraveListController.java - doDeleteGrave");
        this.graveToDelete = grave;
        commitDeleteGrave();
    }

    private void commitDeleteGrave() {
        graveDeleteEventSrc.fire(graveToDelete);
    }

    public String doOk() {
        return Pages.CEMETERY_LIST;
    }

    public List<Grave> getGraves() {
        System.out.println("GraveListController - List<Grave> getGraves(). Cemetery is: " + cemetery.getName());
        graves = graveService.getGraveList(cemetery);
        return graves;
    }

    public void setGraves(List<Grave> graves) {
        this.graves = graves;
    }

}
