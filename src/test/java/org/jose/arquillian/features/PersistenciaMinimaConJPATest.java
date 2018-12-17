package org.jose.arquillian.features;

import java.util.List;

import javax.inject.Inject;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.jose.jaxrs.domain.ReglaCalculo;
import org.jose.jaxrs.dao.ReglasCalculoDao;

/**
 * Característica: Persistencia mínima con JPA
 *  Como desarrollador
 *  Quiero acceder a las reglas de cálculo mediante JPA
 *  Para abstraerme del acceso a BD
 */
@RunWith(Arquillian.class)
public class PersistenciaMinimaConJPATest {

    @Deployment
    public static Archive<?> createDeployment() {

      PomEquippedResolveStage pom = Maven.configureResolver().workOffline().loadPomFromFile("pom.xml");

      return ShrinkWrap.create(WebArchive.class)
              .addPackages(true,"org.jose.jaxrs.domain")
              .addPackages(true,"org.jose.jaxrs.dao")
              .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
              .addAsWebInfResource("jbossas-ds.xml")
              .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void antecedentes() throws Exception {
      dado_que_existe_una_regla_de_calculo_de_prueba_en_la_tabla();
    }

    private void dado_que_existe_una_regla_de_calculo_de_prueba_en_la_tabla() throws Exception {
      utx.begin();
      em.joinTransaction();

      ReglaCalculo rc = new ReglaCalculo("Prueba");
      em.persist(rc);

      utx.commit();
    }

    @EJB
    private ReglasCalculoDao reglasCalculoDao;

    @Test
    public void escenario_acceder_a_una_regla_de_calculo_en_la_tabla() throws Exception {
      // cuando accedo a todas las reglas de cálculo
      List<ReglaCalculo> reglaCalculoList = reglasCalculoDao.getAll();

      // entonces, debe existir UNA regla de cálculo
      assertNotNull(reglaCalculoList);
      assertThat(reglaCalculoList.size(), is(1));

      // y la descripción de la regla de cálculo debe ser correcta
      assertThat(reglaCalculoList.get(0).getDs_regla(), is("Prueba"));
    }
}
