package de.lengsfeld.vr.data;

import de.lengsfeld.vr.model.Image;
import de.lengsfeld.vr.services.ImageServiceBean;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@RequestScoped
@Named("graveImages")
public class GraveImages implements Serializable {

  @Inject
  private ImageServiceBean imageServiceBean;

  private StreamedContent image;

  public StreamedContent getImage() {
    FacesContext context = FacesContext.getCurrentInstance();

    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
      // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
      return new DefaultStreamedContent();
    }
    else {
      // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
      String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
      Image image = imageServiceBean.findImage(Long.valueOf(imageId));
      return new DefaultStreamedContent(new ByteArrayInputStream(image.getImageData()));
    }
  }
  public void setImage(StreamedContent streamedContent){
    this.image = streamedContent;
  }

}
