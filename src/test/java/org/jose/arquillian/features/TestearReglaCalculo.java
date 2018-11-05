package org.jose.arquillian.features;

import java.lang.Throwable;
import java.io.File;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;
import org.jose.jaxrs.model.cucumber.ReglaCalculoMarkdownToTest;
import org.jose.jaxrs.model.cucumber.StringFeatureSupplier;
import org.jose.jaxrs.model.cucumber.RuntimeAdapter;

@RunWith(Arquillian.class)
public class TestearReglaCalculo {

  final Logger logger = LoggerFactory.getLogger(TestearReglaCalculo.class);

  @Deployment
  public static Archive<?> createDeployment() {

    PomEquippedResolveStage pom = Maven.configureResolver().workOffline().loadPomFromFile("pom.xml");

    return ShrinkWrap.create(WebArchive.class)
            .addPackages(true,"org.jose")
            .addAsLibraries(pom.resolve("io.cucumber:cucumber-junit").withTransitivity().asFile())
            .addAsLibraries(pom.resolve("io.cucumber:cucumber-java").withTransitivity().asFile())
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Inject
  ReglaCalculoMarkdownToTest reglaCalculo2Test;

  @Test
  public void ejecucion_de_test_en_cucumber_embebido_en_la_aplicacion() throws Exception {
    // dado que tenemos la siguiente regla de cálculo
    reglaCalculo2Test.setReglaCalculo("r.setPrincipal( o[\"concepto1.ml\"] * t[\"concepto1.precio\"] )" );

    // dado que tenemos el siguiente test en gherkin
    String textoTest = "# language: es "+ System.lineSeparator() +
      "Característica: Test" + System.lineSeparator() +
      "  Como responsable de las reglas de cálculo" + System.lineSeparator() +
      "  Quiero comprobar que son correctas" + System.lineSeparator() +
      "  Para asegurar la calidad de mis cálculos" + System.lineSeparator() +
      "Escenario:" + System.lineSeparator() +
      "  Dado que tenemos cargada la regla de calculo a comprobar" + System.lineSeparator() +
      "    Y que tenemos un objeto con los siguientes valores:" + System.lineSeparator() +
      "        | concepto1.ml | 15.0 |" + System.lineSeparator() +
      "    Y que tenemos la siguiente tarifa simple:" + System.lineSeparator() +
      "        | concepto1.precio | 123.45 |" + System.lineSeparator() +
      "  Cuando ejecuto la regla de cálculo" + System.lineSeparator() +
      "  Entonces la liquidación resultado debe tener un principal igual a 1851.75 Euros" + System.lineSeparator();

    StringFeatureSupplier features = new StringFeatureSupplier();

    features.addStringFeature(textoTest);

    // cuando ejecuto el test de la regla de regla de cálculo
    RuntimeAdapter runtime = new RuntimeAdapter();

    try {
      runtime.test(features);
    }
    catch (Throwable e){
      e.printStackTrace();
      //throw e;
    }

    // entonces el resultado del test debe ser 0
    assertTrue(runtime.exitStatus == 0);
  }

}
