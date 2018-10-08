package org.jose.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = {"progress","html:target/cucumber"},
  features = {
              "classpath:cucumber/markdown/",
              "classpath:cucumber/liquidacion/",
              "classpath:cucumber/tarifa/",
              "classpath:cucumber/tests/"
             },
  glue = {"org.jose.bdd.steps.reglacalculo"}
)

public class RunReglaCalculoMarkdownTest {
}
