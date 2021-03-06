package org.jose.arquillian;

import java.net.URL;
import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.javamoney.moneta.Money;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.Assert;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

import org.jose.jaxrs.model.LiquidacionImpl;
import org.jose.jaxrs.api.ClientCustomJsonProvider;

@RunWith(Arquillian.class)
public class TestArquillian {

  private static final String RESOURCE_PREFIX = "api";

  Response response;

  @Deployment
  public static Archive<?> createDeployment() {

    PomEquippedResolveStage pom = Maven.configureResolver().workOffline().loadPomFromFile("pom.xml");

    return ShrinkWrap.create(WebArchive.class)
            .addPackages(true,"org.jose.jaxrs.model")
            .addPackages(true,"org.jose.jaxrs.server")
            .addPackages(true,"org.jose.jaxrs.util")
            .addPackages(true,"org.jose.jaxrs.api")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsLibraries(pom.resolve("com.atlassian.commonmark:commonmark").withTransitivity().asFile())
            .addAsLibraries(pom.resolve("org.codehaus.groovy:groovy").withTransitivity().asFile())
            .addAsLibraries(pom.resolve("com.fasterxml.jackson.core:jackson-databind").withTransitivity().asFile())
            .addAsLibraries(pom.resolve("org.zalando:jackson-datatype-money").withTransitivity().asFile())
            .addAsLibraries(pom.resolve("org.javamoney:moneta:pom:?").withTransitivity().asFile());
  }

  @ArquillianResource
  URL deploymentUrl;

  @Test
  @RunAsClient
  public void testOnClient() {
    baseURI = deploymentUrl.toString() + RESOURCE_PREFIX;
    response = given()
                  .contentType("application/json")
                  .when()
                  .get("/test/testMinGroovy");

    response.then().assertThat().statusCode(200);
    response.then().body("test", equalTo("4"));
  }

  /**
   * Característica: POJO liquidación resultado en el lado cliente
   *  Como integrador
   *  Quiero un objeto plano java (POJO) que refleje el resultado de la liquidación
   *  Para que el API sea sencillo de integrar en otras aplicaciones
   *  Tener en cuenta:
   *    - servicio jaxrs
   *    - la liquidación en formato json
   *    - se traduce a POJO
   */
  @Test
  @RunAsClient
  public void testLiquidacionPatronOnClient() {

    // DAda que tenemos un servicio jaxrs que devuelve una liquidación patrón
    baseURI = deploymentUrl.toString() + RESOURCE_PREFIX;
    String target = "/test/testLiquidacion";

    // cuando pruebo el servicio jaxrs
    response = given()
                  .contentType("application/json")
                .when()
                  .get(target);

    // entonces el servicio responde correctamente
    response.then().assertThat().statusCode(200);

    // log
    response.then().log().body();

    // cliente jaxrs
    Client client = ClientBuilder.newClient().register(ClientCustomJsonProvider.class);

    // cuando invoco el servicio con un cliente jaxrs y deserializo la Liquidación
    LiquidacionImpl l = client.target(baseURI + target)
                  .request(MediaType.APPLICATION_JSON)
                  .get(LiquidacionImpl.class);

    // entonces la liquidación debe ser la esperada
    assertTrue(l.getPrincipal().compareTo(Money.of(110.01,"EUR")) == 0);
    assertTrue(l.getBaseImponible().compareTo(Money.of(100.01,"EUR")) == 0);
    assertTrue(l.getTipoIva() == 10);
    assertTrue(l.getC().containsKey("Concepto 1"));
    assertTrue(l.getC().containsKey("Concepto 2"));
    assertTrue(l.getC().containsKey("Concepto 3"));

    client.close();
  }

}
