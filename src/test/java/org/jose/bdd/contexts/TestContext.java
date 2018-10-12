package org.jose.bdd.contexts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;

public class TestContext {
  public ReglaCalculoMarkdown reglaCalculo;

  final Logger logger = LoggerFactory.getLogger(TestContext.class);

  public boolean iniciado() {
    return reglaCalculo != null;
  }

  public void borrar() {
    reglaCalculo = null;
  }

}
