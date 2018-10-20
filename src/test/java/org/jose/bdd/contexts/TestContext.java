package org.jose.bdd.contexts;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;

@Singleton
public class TestContext {
  private ReglaCalculoMarkdown reglaCalculo;

  final Logger logger = LoggerFactory.getLogger(TestContext.class);

  public ReglaCalculoMarkdown getReglaCalculo() {
    return reglaCalculo;
  }

  public void setReglaCalculo(ReglaCalculoMarkdown reglaCalculo) {
    this.reglaCalculo = reglaCalculo;
  }

  public void iniciar() {
    reglaCalculo = new ReglaCalculoMarkdown();
  }

  public boolean iniciado() {
    return reglaCalculo != null;
  }

  public void borrar() {
    reglaCalculo = null;
  }

}
