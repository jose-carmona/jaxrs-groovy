package org.jose.jaxrs.model;

import java.math.BigDecimal;
import java.util.Map;

import javax.money.MonetaryAmount;

public interface Liquidacion {

  public Map getC();

  public MonetaryAmount getPrincipal();
  public void setPrincipal( BigDecimal importe );

  public MonetaryAmount getBaseImponible();
  public void setBaseImponible( BigDecimal importe );

  public MonetaryAmount getIva();

  public int getTipoIva();
  public void setTipoIva( int tipoIva );

  public void aplicarIva();

}
