package org.jose.jaxrs.model.cucumber;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import cucumber.runtime.FeatureSupplier;
import cucumber.runtime.Runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
public class ReglaCalculoMarkdownToTest implements Serializable {

  private String reglaCalculo;

  public String getReglaCalculo() {
    return reglaCalculo;
  }

  public void setReglaCalculo(String reglaCalculo) {
    this.reglaCalculo = reglaCalculo;
  }
}
