package org.jose.bdd.steps.markdown;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Cuando;

import cucumber.api.PendingException;

import static org.junit.Assert.*;

import org.jose.jaxrs.api.ReglaCalculo;

public class StepsMarkdown {

  private ReglaCalculo reglaCalculo;

  @Before
  public void setUp() throws Throwable {
    reglaCalculo = new ReglaCalculo();
  }

  @After
  public void afterScenario() throws Throwable {
  }

  @Dado("^que tenemos la siguiente regla de cálculo:$")
  public void que_tenemos_la_siguiente_regla_de_calculo(String texto) throws Throwable {
    reglaCalculo.setMarkdown( texto );
  }

  @Dado("^que asignamos a la variable \"([^\"]*)\" el valor numérico (\\d+)$")
  public void que_asignamos_a_la_variable_el_valor_numérico(String var, int valor) throws Throwable {
    reglaCalculo.setVariable(var, new Integer(valor));
  }

  @Cuando("^ejecuto la regla de cálculo$")
  public void ejecuto_la_regla_de_cálculo() throws Throwable {
    reglaCalculo.calcular();
  }

  @Entonces("^el resultado de la regla de cálculo debe ser \"([^\"]*)\"$")
  public void el_resultado_de_la_regla_de_calculo_debe_ser(String esperado) throws Throwable {
    assertEquals(esperado, reglaCalculo.getResultado());
  }

  @Entonces("^el script groovy de la regla de cálculo debe ser:$")
  public void el_script_groovy_de_la_regla_de_calculo_debe_ser(String esperado) throws Throwable {
    assertEquals(esperado, reglaCalculo.getScript());
  }

  @Entonces("^el HTML de la regla de cálculo debe ser:$")
  public void el_HTML_de_la_regla_de_calculo_debe_ser(String esperado) throws Throwable {
    assertEquals(esperado, reglaCalculo.getHtml());
  }

}