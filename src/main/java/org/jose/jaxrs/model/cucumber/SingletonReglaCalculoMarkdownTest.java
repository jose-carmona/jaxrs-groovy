package org.jose.jaxrs.model.cucumber;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;

import cucumber.runtime.FeatureSupplier;
import cucumber.runtime.Runtime;

public class SingletonReglaCalculoMarkdownTest {

  private static SingletonReglaCalculoMarkdownTest instance;

  public ReglaCalculoMarkdown reglaCalculo;
  public byte exitStatus;

  private SingletonReglaCalculoMarkdownTest() {
    instance = this;
  }

  public static SingletonReglaCalculoMarkdownTest getInstace() {
    if (instance == null) {
      instance = new SingletonReglaCalculoMarkdownTest();
    }
    return instance;
  }

  public boolean iniciado() {
    return reglaCalculo != null;
  }

  public void borrar() {
    reglaCalculo = null;
  }

  public void test(FeatureSupplier features) {
    final Runtime runtime = Runtime.builder()
                    .withFeatureSupplier(features)
                    .withArg("--glue org.jose.bdd.steps.reglacalculo")
                    .build();
    runtime.run();
    exitStatus = runtime.exitStatus();
  }
}
