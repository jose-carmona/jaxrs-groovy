package org.jose.jaxrs.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.LinkedHashMap;

import javax.money.MonetaryAmount;
import javax.money.MonetaryRounding;
import javax.money.Monetary;
import javax.money.RoundingQueryBuilder;

import org.javamoney.moneta.Money;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiquidacionImpl implements Liquidacion {

  final Logger logger = LoggerFactory.getLogger(LiquidacionImpl.class);

  public LinkedHashMap c;  // conceptos de la Liquidación

  private MonetaryRounding rounding;

  private MonetaryAmount principal;

  private MonetaryAmount baseImponible;
  private MonetaryAmount iva;
  private int tipoIva;

  public LiquidacionImpl() {
    c = new LinkedHashMap();
    rounding = Monetary.getRounding(RoundingQueryBuilder.of().setScale(2).set(RoundingMode.HALF_UP).build());
  }

  public Map getConceptos() {
    return c;
  }

  public MonetaryAmount getPrincipal() {
    return principal;
  }

  public void setPrincipal( BigDecimal importe ) {
    principal = Money.of( importe, "EUR" );
    principal = principal.with(rounding);
  }

  public MonetaryAmount getBaseImponible() {
    return baseImponible;
  }

  public void setBaseImponible( BigDecimal importe ) {
    baseImponible = Money.of( importe, "EUR");
    baseImponible = baseImponible.with(rounding);
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
    iva = iva.with(rounding);
    principal = baseImponible.add(iva);
  }
}
