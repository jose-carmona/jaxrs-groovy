package org.jose.jaxrs.model.cucumber;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;

import cucumber.runtime.FeatureSupplier;
import cucumber.runtime.Runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonReglaCalculoMarkdownTest {

  final Logger logger = LoggerFactory.getLogger(SingletonReglaCalculoMarkdownTest.class);

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

  public void test(FeatureSupplier features) {
    logger.debug("test!");
    logger.debug(reglaCalculo.getScript());
    final Runtime runtime = Runtime.builder()
                    .withFeatureSupplier(features)
                    .withArg("--glue org.jose.bdd.steps.reglacalculo")
                    .build();
    runtime.run();
    exitStatus = runtime.exitStatus();
  }
}
