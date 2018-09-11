package org.jose.bdd.steps;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Cuando;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Steps {

  RequestSpecification rs;
  Response response;

  @Before
  public void setUp() {
    baseURI = "https://127.0.0.1:8080";
    rs = given();
  }

  @After
  public void afterScenario() {
  }

  @Cuando("^invoco al test")
  public void invoco_al_test() throws Throwable {
    response = rs.when().get("/calc/script/test");
    response.then().assertThat().statusCode(200);
  }

  @Entonces("^el API debe devolver un statusCode (\\d+)$")
  public void el_API_debe_devolver_un_statusCode(int code) throws Throwable {
    response.then().assertThat().statusCode(code);
  }

  @Entonces("el resultado debe ser \"([^\"]*)\"$")
  public void el_API_de_personajes_de_Rick_y_Morty_debe_mostrar_el_nombre(String name) throws Throwable {
    response.then().body("result", equalTo(name));
  }


}
