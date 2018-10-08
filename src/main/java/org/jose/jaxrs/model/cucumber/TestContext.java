package org.jose.jaxrs.model.cucumber;

import javax.inject.Singleton;
import cucumber.runtime.java.guice.ScenarioScoped;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;

@ScenarioScoped
public class TestContext {
  public ReglaCalculoMarkdown reglaCalculo;

  public boolean iniciado() {
    return reglaCalculo != null;
  }

  public void borrar() {
    reglaCalculo = null;
  }

}
