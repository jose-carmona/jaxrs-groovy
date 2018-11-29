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
// import io.restassured.http.ContentType;
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
            .addPackages(true,"org.jose")
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

  @Test
  @RunAsClient
  public void testLiquidacionOnClient() {
    baseURI = deploymentUrl.toString() + RESOURCE_PREFIX;
    String target = "/test/testLiquidacion";

    response = given()
                  .contentType("application/json")
                .when()
                  .get(target);

    response.then().assertThat().statusCode(200);
    response.then().log().body();
    // response.then().body("principal.amount", equalTo("100.01"));

    // cliente jaxrs
    Client client = ClientBuilder.newClient().register(ClientCustomJsonProvider.class);


    LiquidacionImpl l = client.target(baseURI + target)
                  .request(MediaType.APPLICATION_JSON)
                  .get(LiquidacionImpl.class);

    assertTrue(l.getPrincipal().compareTo(Money.of(110.01,"EUR")) == 0);
    assertTrue(l.getC().containsKey("Concepto 1"));
    assertTrue(l.getC().containsKey("Concepto 2"));
    assertTrue(l.getC().containsKey("Concepto 3"));

    client.close();
  }

}
