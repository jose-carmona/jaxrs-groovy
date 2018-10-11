package org.jose.bdd.steps.reglacalculo;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.javamoney.moneta.Money;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Cuando;

import cucumber.api.PendingException;

import io.cucumber.datatable.DataTable;

import static org.junit.Assert.*;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;
import org.jose.jaxrs.model.TarifaSimple;
import org.jose.jaxrs.model.cucumber.StringFeatureSupplier;
import org.jose.jaxrs.model.cucumber.SingletonReglaCalculoMarkdownTest;

import org.jose.bdd.contexts.TestContext;

public class StepsTestReglaCalculoMarkdown {

  final Logger logger = LoggerFactory.getLogger(StepsTestReglaCalculoMarkdown.class);

  private ReglaCalculoMarkdown reglaCalculo;
  private TestContext contexto;
  private StringFeatureSupplier features;

  public StepsTestReglaCalculoMarkdown(TestContext testContext) {
    contexto = testContext;
    if (!contexto.iniciado()) {
      contexto.reglaCalculo = new ReglaCalculoMarkdown();
    }

    features = new StringFeatureSupplier();
  }

  @Before
  public void setUp() throws Throwable {
  }

  @After
  public void afterScenario() throws Throwable {
  }

  @Dado("que tenemos el siguiente test:")
  public void que_tenemos_el_siguiente_test(String docString) throws Throwable {
    features.addStringFeature(docString);
  }

  @Cuando("ejecuto el test de la regla de cálculo")
  public void ejecuto_el_test_de_la_regla_de_calculo() {
    SingletonReglaCalculoMarkdownTest singleton = SingletonReglaCalculoMarkdownTest.getInstace();
    singleton.reglaCalculo = contexto.reglaCalculo;
    singleton.test(features);
  }

  @Entonces("el resultado del test es correcto")
  public void el_resultado_del_test_es_correcto() {
    SingletonReglaCalculoMarkdownTest singleton = SingletonReglaCalculoMarkdownTest.getInstace();
    assertTrue(singleton.exitStatus==0);
  }

}
