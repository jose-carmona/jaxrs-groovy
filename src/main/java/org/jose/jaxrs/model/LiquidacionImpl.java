package org.jose.jaxrs.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.LinkedHashMap;

import javax.money.MonetaryOperator;

import javax.money.MonetaryAmount;
import javax.money.Monetary;

import org.javamoney.moneta.Money;


public class LiquidacionImpl implements Liquidacion {

  public LinkedHashMap c;  // conceptos de la Liquidación

  private MonetaryAmount principal;

  private MonetaryAmount baseImponible;
  private MonetaryAmount iva;
  private int tipoIva;

  public LiquidacionImpl() {
    c = new LinkedHashMap();
  }

  public Map getConceptos() {
    return c;
  }

  public MonetaryAmount getPrincipal() {
    return principal;
  }

  public void setPrincipal( BigDecimal importe ) {
    principal = Money.of( importe, "EUR");
    principal = principal.with(Monetary.getDefaultRounding());
  }

  public MonetaryAmount getBaseImponible() {
    return baseImponible;
  }

  public void setBaseImponible( BigDecimal importe ) {
    baseImponible = Money.of( importe, "EUR");
    baseImponible = baseImponible.with(Monetary.getDefaultRounding());
  }

  public MonetaryAmount getIva() {
    return iva;
  }

  public void setTipoIva( int tipoIva ) {
    this.tipoIva = tipoIva;
  }

  public int getTipoIva() {
    return tipoIva;
  }

  public void aplicarIva() {
    BigDecimal tipoIvaTantoPor1 = new BigDecimal(tipoIva);
    tipoIvaTantoPor1 = tipoIvaTantoPor1.divide(new BigDecimal(100));
    iva = baseImponible.multiply(tipoIvaTantoPor1);
    iva = iva.with(Monetary.getDefaultRounding());
    principal = baseImponible.add(iva);
  }
}
