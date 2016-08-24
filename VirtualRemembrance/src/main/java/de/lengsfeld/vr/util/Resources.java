package de.lengsfeld.vr.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class Resources {

    @Produces
    @PersistenceContext(unitName="VR")
    private EntityManager em;
    
    @Produces
    public Logger produceLog() {
        return Logger.getLogger("MyLogger");
    }

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    @RequestScoped
    HttpServletRequest produceRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
