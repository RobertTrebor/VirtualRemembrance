package de.lengsfeld.vr.services;

import de.lengsfeld.vr.model.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class ImageServiceBean implements Serializable {

    @PersistenceContext(unitName="VR")
    EntityManager entityManager;

    @Transactional
    public void uploadImage(Image image){
        System.out.println("ImageServiceBean.java - uploadImage: image" + image);
        image.setImageData(getImage(image));
        entityManager.persist(image);
    }

    private byte[] getImage(Image image){
        File file = new File(image.getFileName());
        if(file.exists()){
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", outputStream);
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Image findImage(long id){
        return entityManager.find(Image.class, id);
    }

}