package de.lengsfeld.vr.controller;

import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Grave;
import de.lengsfeld.vr.model.Image;
import de.lengsfeld.vr.util.Events.Added;
import de.lengsfeld.vr.util.Events.Updated;
import de.lengsfeld.vr.util.Events.Uploaded;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class GraveEditController implements Serializable {

    @Inject
    @Added
    private Event<Grave> graveAddEventSrc;

    @Inject
    @Updated
    private Event<Grave> graveUpdatedEventSrc;

    @Inject
    @Uploaded
    private Event<Image> imageUploadedEventSrc;

    public enum Mode {
        EDIT, ADD
    };

    private Cemetery cemetery;
    private Grave grave;
    private Mode mode;
    private UploadedFile uploadedFile;

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
        this.grave.setCemetery(cemetery);
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

    public void doUpload(FileUploadEvent event) throws IOException {
        UploadedFile uploadedFile = event.getFile();
        BufferedImage bufferedImage = ImageIO.read(uploadedFile.getInputstream());
        String filename = "c_id" + bufferedImage.getWidth() + bufferedImage.getHeight() + ".jpg";
        File imagesDir = new File(System.getProperty("jboss.server.data.dir"), "images");
        imagesDir.mkdir();
        File tempFile = new File(imagesDir, filename );
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        ImageIO.write(bufferedImage, "JPG", outputStream);


        Image image = new Image();
        image.setGrave(grave);
        image.setFileName(imagesDir + "\\" + filename);

        if(grave.getImages() == null){
            List<Image> images = new ArrayList<>();
            grave.setImages(images);
        }
        grave.getImages().add(image);
        imageUploadedEventSrc.fire(image);
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

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
