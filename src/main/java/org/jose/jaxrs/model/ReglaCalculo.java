package org.jose.jaxrs.model;

import org.jose.jaxrs.model.Liquidacion;

public interface ReglaCalculo {

  public Liquidacion getLiqResultado();

  public void setVariable(String var, Object value);

  public void calcular();
}
