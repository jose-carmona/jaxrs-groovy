package org.jose.bdd.steps.reglacalculo;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Cuando;

import static org.junit.Assert.*;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;
import org.jose.jaxrs.model.cucumber.StringFeatureSupplier;
import org.jose.jaxrs.model.cucumber.SingletonReglaCalculoMarkdownTest;

import org.jose.bdd.contexts.TestContext;

@Singleton
public class StepsTestReglaCalculoMarkdown {

  final Logger logger = LoggerFactory.getLogger(StepsTestReglaCalculoMarkdown.class);

  @Inject private TestContext contexto;

  private ReglaCalculoMarkdown reglaCalculo;
  private StringFeatureSupplier features;

  public StepsTestReglaCalculoMarkdown() {
    if (contexto == null){
      logger.debug("creo contexto");
      contexto = new TestContext();
    }

    if (!contexto.iniciado()) {
      logger.debug("inicio contexto");
      contexto.iniciar();
    }
    reglaCalculo = contexto.getReglaCalculo();
    features = new StringFeatureSupplier();
  }

  @Dado("que tenemos el siguiente test:")
  public void que_tenemos_el_siguiente_test(String docString) throws Throwable {
    features.addStringFeature(docString);
  }

  @Cuando("ejecuto el test de la regla de cálculo")
  public void ejecuto_el_test_de_la_regla_de_calculo() {
    SingletonReglaCalculoMarkdownTest singleton = SingletonReglaCalculoMarkdownTest.getInstace();
    singleton.reglaCalculo = reglaCalculo;
    singleton.test(features);
  }

  @Entonces("el resultado del test es correcto")
  public void el_resultado_del_test_es_correcto() {
    SingletonReglaCalculoMarkdownTest singleton = SingletonReglaCalculoMarkdownTest.getInstace();
    assertTrue(singleton.exitStatus==0);
  }

}
