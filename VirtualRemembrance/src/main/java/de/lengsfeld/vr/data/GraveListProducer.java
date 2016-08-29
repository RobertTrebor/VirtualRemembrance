package de.lengsfeld.vr.data;

import de.lengsfeld.vr.model.Grave;
import de.lengsfeld.vr.services.GraveService;
import de.lengsfeld.vr.util.Events.Added;
import de.lengsfeld.vr.util.Events.Deleted;
import de.lengsfeld.vr.util.Events.Updated;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@RequestScoped
public class GraveListProducer {

    private List<Grave> graves;
    
    @Inject
    private GraveService graveService;

    @Produces
    @Named
    public List<Grave> getGraves() {
        return graves;
    }
    
    public void onGraveAdded(@Observes @Added Grave grave) {
        System.out.println("GraveListProducer.java - onGraveAdded");
        graveService.addGrave(grave);
    }
    
    public void onGraveDeleted(@Observes @Deleted Grave grave) {
        System.out.println("GraveListProducer.java - onGraveDeleted");
        graveService.deleteGrave(grave);
    }
    
    public void onGraveUpdated(@Observes @Updated Grave grave) {
        System.out.println("GraveListProducer.java - onGraveUpdated");
        graveService.updateGrave(grave);
    }
}
