package de.lengsfeld.vr.data;

import de.lengsfeld.virtualremembrance.Cemetery;
import de.lengsfeld.virtualremembrance.services.CemeteryService;
import de.lengsfeld.virtualremembrance.util.Events.Added;
import de.lengsfeld.virtualremembrance.util.Events.Deleted;
import de.lengsfeld.virtualremembrance.util.Events.Updated;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class CemeteryListProducer {

    private List<Cemetery> cemeteries;
    
    @Inject
    private CemeteryService cemeteryService;
    
    @PostConstruct
    public void init() {
        cemeteries = cemeteryService.getAllCemeteries();
        System.out.println("CemeteryListProducer: DO WE GET ANY DATA!!" + cemeteries);
    }
    
    @Produces
    @Named
    public List<Cemetery> getCemeteries() {
        return cemeteries;
    }
    
    public void onCemeteryAdded(@Observes @Added Cemetery cemetery) {
        System.out.println("CemeteryListProducer-onCemeteryAdded");
        cemeteryService.addCemetery(cemetery);
        init();
    }
    
    public void onCemeteryDeleted(@Observes @Deleted Cemetery cemetery) {
        System.out.println("CemeteryListProducer-onCemeteryDeleted");
        cemeteryService.deleteCemetery(cemetery);
        init();
    }
    
    public void onCemeteryUpdated(@Observes @Updated Cemetery cemetery) {
        System.out.println("CemeteryListProducer-onCemeteryUpdated");
        cemeteryService.updateCemetery(cemetery);
        init();
    }
}