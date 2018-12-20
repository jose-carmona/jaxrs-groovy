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
import org.jose.jaxrs.domain.Publicacion;
import org.jose.jaxrs.dao.ReglasCalculoDao;

/**
 * Característica: Persistencia mínima con JPA
 *  Como desarrollador
 *  Quiero acceder a las reglas de cálculo mediante JPA
 *  Para abstraerme del acceso a BD
 *
 * @author @jose_carmona
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

  // regla de cálculo 1
  String textoReglaCalculo1 = "```" + System.lineSeparator() +
    "r.setPrincipal( o[\"concepto1.ml\"] * t[\"concepto1.precio\"] )" + System.lineSeparator() +
    "```" + System.lineSeparator();

  // regla de cálculo 2
  String textoReglaCalculo2 = "```" + System.lineSeparator() +
    "r.setPrincipal( o[\"concepto2.ml\"] * t[\"concepto2.precio\"] )" + System.lineSeparator() +
    "```" + System.lineSeparator();

  private void dado_que_existen_dos_regla_de_calculo_de_prueba_en_la_tabla() throws Exception {
    utx.begin();
    em.joinTransaction();

    ReglaCalculo rc1 = new ReglaCalculo(1L, "Prueba 1", textoReglaCalculo1);
    em.persist(rc1);

    ReglaCalculo rc2 = new ReglaCalculo(2L, "Prueba 2", textoReglaCalculo2);
    em.persist(rc2);

    utx.commit();
  }

  private void dado_que_tenemos_asignada_la_regla_de_calculo_a_la_entidad_para_un_moodelo(
                  String cd_entidad, String cd_modelo, long cdi_regla) throws Exception {
    utx.begin();
    em.joinTransaction();

    Publicacion p = new Publicacion(cd_entidad,cd_modelo,cdi_regla);
    em.persist(p);

    utx.commit();
  }

  @Before
  public void antecedentes() throws Exception {
    dado_que_existen_dos_regla_de_calculo_de_prueba_en_la_tabla();
    dado_que_tenemos_asignada_la_regla_de_calculo_a_la_entidad_para_un_moodelo("001","01",1L);
    dado_que_tenemos_asignada_la_regla_de_calculo_a_la_entidad_para_un_moodelo("002","02",2L);
  }

  @EJB
  private ReglasCalculoDao reglasCalculoDao;

  /**
  * Escenario: Acceso a una regla de cálculo
  *
  * @author @jose_carmona
  *
  *  - Acceso por CDI
  *  - Acceso por Entidad y Modelo
  */
  @Test
  public void escenario_acceder_a_una_regla_de_calculo() throws Exception {
    // cuando accedo a la regla de cálculo 1
    ReglaCalculo rc = reglasCalculoDao.getByCdi_regla(1L);

    // y la descripción de la regla de cálculo debe ser correcta
    assertThat(rc.getDs_regla(), is("Prueba 1"));

    // y el texto Markdoown de la regla de cálculo debe ser correcto
    assertThat(rc.getMd_regla(), is(textoReglaCalculo1));

    // cuando accedo a la regla de calculo para la Entidad y modelo
    rc = reglasCalculoDao.getByCd_entidadCd_modelo("001","01");

    // y la descripción de la regla de cálculo debe ser correcta
    assertThat(rc.getDs_regla(), is("Prueba 1"));

    // y el texto Markdoown de la regla de cálculo debe ser correcto
    assertThat(rc.getMd_regla(), is(textoReglaCalculo1));

    // cuando accedo a la regla de calculo para la Entidad y modelo
    rc = reglasCalculoDao.getByCd_entidadCd_modelo("002","02");

    // y la descripción de la regla de cálculo debe ser correcta
    assertThat(rc.getDs_regla(), is("Prueba 2"));

    // y el texto Markdoown de la regla de cálculo debe ser correcto
    assertThat(rc.getMd_regla(), is(textoReglaCalculo2));
  }

}
