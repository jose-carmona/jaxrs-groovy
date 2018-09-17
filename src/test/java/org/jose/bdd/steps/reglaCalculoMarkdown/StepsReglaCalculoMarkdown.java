package org.jose.bdd.steps.markdown;

import java.math.BigDecimal;

import org.javamoney.moneta.Money;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Cuando;

import cucumber.api.PendingException;

import static org.junit.Assert.*;

import org.jose.jaxrs.api.ReglaCalculoMarkdown;

public class StepsReglaCalculoMarkdown {

  private ReglaCalculoMarkdown reglaCalculo;

  @Before
  public void setUp() throws Throwable {
    reglaCalculo = new ReglaCalculoMarkdown();
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

  @Entonces("la liquidación resultado debe tener un principal igual a {bigdecimal} Euros")
  public void la_liquidacion_resultado_debe_tener_un_principal_igual_a_Euros(BigDecimal esperado) throws Throwable {
    assertEquals(Money.of(esperado, "EUR"), reglaCalculo.getLiqResultado().getPrincipal());
  }

  @Entonces("la liquidación resultado debe tener un IVA igual a {bigdecimal} Euros")
  public void la_liquidacion_resultado_debe_tener_un_IVA_igual_a_Euros(BigDecimal esperado) throws Throwable {
    assertEquals(Money.of(esperado, "EUR"), reglaCalculo.getLiqResultado().getIva());
  }

}