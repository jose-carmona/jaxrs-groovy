package org.jose.arquillian;

import java.net.URL;
import java.io.File;

import javax.inject.Inject;

import javax.ws.rs.ApplicationPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(Arquillian.class)
public class TestArquillian {

  private static final String RESOURCE_PREFIX = "api";

  private final Logger logger = LoggerFactory.getLogger(TestArquillian.class);

  Response response;

  @Deployment
  public static Archive<?> createDeployment() {

    PomEquippedResolveStage pom = Maven.configureResolver().workOffline().loadPomFromFile("pom.xml");

    return ShrinkWrap.create(WebArchive.class)
            .addPackages(true,"org.jose")
            .addAsLibraries(pom.resolve("org.codehaus.groovy:groovy").withTransitivity().asFile())
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @ArquillianResource
  URL deploymentUrl;

  @Test
  @RunAsClient
  public void testOnClient() {
    logger.debug("testOnClient");
    baseURI = deploymentUrl.toString() + RESOURCE_PREFIX;
    System.out.println("baseURI" + baseURI);
    response = given()
                  .contentType("application/json")
                  .when()
                  .get("/calc/test");

    response.then().assertThat().statusCode(200);
    response.then().body("test", equalTo("4"));
  }
}
