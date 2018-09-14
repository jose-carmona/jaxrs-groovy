package org.jose.bdd.steps;

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

public class Steps {

  EmbeddedServer server;
  RequestSpecification rs;
  Response response;

  @Before
  public void setUp() throws Throwable {
    baseURI = "http://127.0.0.1:8680";

    this.server = new EmbeddedServer();
    this.server.start();

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

  @Entonces("^devolver un tipo de contenido json$")
  public void devolver_un_tipo_de_contenido_json() throws Throwable {
    response.then().assertThat().contentType(ContentType.JSON);
  }

  @Entonces("el resultado debe ser \"([^\"]*)\"$")
  public void el_resultado_debe_ser(String rt) throws Throwable {
    response.then().body("test", equalTo(rt));
  }



}
