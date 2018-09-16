package org.jose.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        features = {"classpath:cucumber/markdown/","classpath:cucumber/liquidacion/"},
        glue = {"org.jose.bdd.steps.markdown"}
)

public class RunMarkdownTest {
}
