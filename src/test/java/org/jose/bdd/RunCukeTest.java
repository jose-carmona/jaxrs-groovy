package org.jose.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = {"progress","html:target/cucumber"},
  features = "classpath:cucumber/REST/",
  glue = {"org.jose.bdd.steps.rest"}
)

public class RunCukeTest {
}
