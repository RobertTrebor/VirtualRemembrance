package de.lengsfeld.vr.controller;

import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Grave;
import de.lengsfeld.vr.util.Events.Added;
import de.lengsfeld.vr.util.Events.Updated;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class GraveEditController implements Serializable {

    @Inject
    @Added
    private Event<Grave> graveAddEventSrc;

    @Inject
    @Updated
    private Event<Grave> graveUpdatedEventSrc;

    public enum Mode {
        EDIT, ADD
    };

    private Cemetery cemetery;
    private Grave grave;
    private Mode mode;

    private Mode getMode() {
        return mode;
    }
    
    public void setGraveToEdit(Mode mode, Cemetery cemetery) {
        setGraveToEdit(mode, new Grave(), cemetery);
    }

    public void setGraveToEdit(Mode mode, Grave grave, Cemetery cemetery) {
        this.grave = grave;
        this.mode = mode;
        this.cemetery = cemetery;
        grave.setCemetery(cemetery);
    }

    public String doSave() {
        if (getMode() == Mode.ADD) {
            System.out.println("GraveEditController.java - doSave, grave: " + grave);
            graveAddEventSrc.fire(grave);
        } else {
            graveUpdatedEventSrc.fire(grave);
        }
        return Pages.GRAVE_LIST;
    }

    public String doCancel() {
        return Pages.GRAVE_LIST;
    }

    public String getTitle() {
        return getMode() == Mode.EDIT ? "Edit Grave" : "Add New Grave";
    }

    public Grave getGrave() {
        return grave;
    }

    public void setGrave(Grave grave) {
        this.grave = grave;
    }
}
