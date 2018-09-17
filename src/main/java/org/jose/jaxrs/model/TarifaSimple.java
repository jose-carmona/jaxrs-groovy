package org.jose.jaxrs.model;

import java.math.BigDecimal;
import java.util.Map;

public class TarifaSimple implements Tarifa {
  private Map<String, BigDecimal> mapa;

  public TarifaSimple( Map<String, BigDecimal> mapa) {
    this.mapa = mapa;
  }

  public BigDecimal v( String concepto ) {
    return mapa.get(concepto);
  }
}
