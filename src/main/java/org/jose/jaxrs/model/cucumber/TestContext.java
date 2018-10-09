package org.jose.jaxrs.model.cucumber;

import org.jose.jaxrs.model.ReglaCalculoMarkdown;

public class TestContext {
  public ReglaCalculoMarkdown reglaCalculo;

  public boolean iniciado() {
    return reglaCalculo != null;
  }

  public void borrar() {
    reglaCalculo = null;
  }

}
