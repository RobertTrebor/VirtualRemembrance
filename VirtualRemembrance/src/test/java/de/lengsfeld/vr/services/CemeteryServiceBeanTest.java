package de.lengsfeld.vr.services;

import de.lengsfeld.vr.data.CemeteryListProducer;
import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.services.CemeteryService;
import de.lengsfeld.vr.services.CemeteryServiceBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by robert on 24.08.16.
 */

@RunWith(Arquillian.class)
public class CemeteryServiceBeanTest {

    @Inject
    private CemeteryService cemeteryService;

        @Deployment
        public static Archive<?> createDeployment() {
            MavenResolverSystem resolver = Maven.resolver();
            resolver.loadPomFromFile("pom.xml");
            //BeansDescriptor beans = Descriptors.create(BeansDescriptor.class).createInterceptors();
            return ShrinkWrap.create(JavaArchive.class)
                    .addClass(CemeteryServiceBean.class)
                    .addClass(CemeteryListProducer.class)
                    .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        }

    @Test
    public void should_be_deployed()
    {
        Assert.assertNotNull(cemeteryService);
    }

    @Test
    public void getAllCemeteries() throws Exception {
        Cemetery cemetery = new Cemetery("Dorotheenstädtischer-Friedrichswerderscher Friedh", "DE");
        System.out.println(cemetery);
        cemetery.setCity("Berlin");
        cemetery.setZipcode("10115");
        cemetery.setStreet("Chausseestr. 126");
        //cemetery.setLatitude(52.52778);
        //cemetery.setLongitude(13.384167);

        cemeteryService.addCemetery(cemetery);
    }

}