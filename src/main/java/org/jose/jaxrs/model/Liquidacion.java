package org.jose.jaxrs.model;

import java.math.BigDecimal;

import javax.money.MonetaryOperator;

import javax.money.MonetaryAmount;
import javax.money.Monetary;

import org.javamoney.moneta.Money;


public class Liquidacion {

  private MonetaryAmount principal;

  private MonetaryAmount baseImponible;
  private MonetaryAmount iva;
  private int tipoIva;

  public String getPrincipal() {
    return this.principal == null ? null : this.principal.toString();
  }

  public String getBaseImponible() {
    return this.baseImponible == null ? null : this.baseImponible.toString();
  }

  public String getIva() {
    return this.iva == null ? null : this.iva.toString();
  }

  public int getTipoIva() {
    return this.tipoIva;
  }

  public void setPrincipal( BigDecimal importe ) {
    this.principal = Money.of( importe, "EUR");
    this.principal = this.principal.with(Monetary.getDefaultRounding());
  }

  public void setBaseImponible( BigDecimal importe ) {
    this.baseImponible = Money.of( importe, "EUR");
    this.baseImponible = this.baseImponible.with(Monetary.getDefaultRounding());
  }

  public void setTipoIva( int tipoIva ) {
    this.tipoIva = tipoIva;

  }

  public void aplicarIva() {
    BigDecimal tipoIvaTantoPor1 = new BigDecimal(tipoIva);
    tipoIvaTantoPor1 = tipoIvaTantoPor1.divide(new BigDecimal(100));
    this.iva = this.baseImponible.multiply(tipoIvaTantoPor1);
    this.iva = this.iva.with(Monetary.getDefaultRounding());
    this.principal = this.baseImponible.add(this.iva);
  }
}
