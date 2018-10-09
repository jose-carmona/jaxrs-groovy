package org.jose.bdd.steps.rest;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Cuando;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.jose.jaxrs.server.EmbeddedServer;

public class StepsREST {

  EmbeddedServer server;
  RequestSpecification rs;
  Response response;

  @Before
  public void setUp() throws Throwable {

    server = new EmbeddedServer();
    server.start();

    baseURI = server.getURI().toString();
    rs = given().contentType("application/json");
  }

  @After
  public void afterScenario() throws Throwable {
    server.stop();
  }

  @Cuando("^invoco al servicio REST de test$")
  public void invoco_al_servicio_REST_de_test() throws Throwable {
    response = rs.when().get("/calc/script/test");
    response.then().assertThat().statusCode(200);
  }

  @Entonces("^el API debe devolver un statusCode (\\d+)$")
  public void el_API_debe_devolver_un_statusCode(int code) throws Throwable {
    response.then().assertThat().statusCode(code);
  }

  @Entonces("^el API debe devolver un tipo de contenido json$")
  public void el_API_debe_devolver_un_tipo_de_contenido_json() throws Throwable {
    response.then().assertThat().contentType(ContentType.JSON);
  }

  @Entonces("^el API debe devolver un resultado igual a \"([^\"]*)\"$")
  public void el_API_debe_devolver_un_resultado_igual_a(String rt) throws Throwable {
    response.then().body("test", equalTo(rt));
  }

}
