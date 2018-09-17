package org.jose.jaxrs.api;

// TODO: mover Liquidacion a interface
import org.jose.jaxrs.model.Liquidacion;

public interface ReglaCalculo {

  public Liquidacion getLiqResultado();

  public void setVariable(String var, Object value);

  public void calcular();
}
