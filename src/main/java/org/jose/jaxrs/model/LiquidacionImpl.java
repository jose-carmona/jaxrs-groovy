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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LiquidacionImpl implements Liquidacion {

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

  public Map getC() {
    return c;
  }

  public MonetaryAmount getPrincipal() {
    return principal;
  }

  @JsonSetter
  public void setPrincipal(MonetaryAmount importe) {
    principal = importe;
    principal = principal.with(rounding);
  }

  public void setPrincipal(BigDecimal importe) {
    principal = Money.of(importe, "EUR");
    principal = principal.with(rounding);
  }

  public MonetaryAmount getBaseImponible() {
    return baseImponible;
  }

  @JsonSetter
  public void setBaseImponible(MonetaryAmount importe) {
    baseImponible = importe;
    baseImponible = baseImponible.with(rounding);
  }

  public void setBaseImponible(BigDecimal importe) {
    baseImponible = Money.of(importe, "EUR");
    baseImponible = baseImponible.with(rounding);
  }

  public MonetaryAmount getIva() {
    return iva;
  }

  public void setIva(MonetaryAmount importe) {
    iva = importe;
  }

  public int getTipoIva() {
    return tipoIva;
  }

  public void setTipoIva(int tipoIva) {
    this.tipoIva = tipoIva;
  }

  public void aplicarIva() {
    BigDecimal tipoIvaTantoPor1 = new BigDecimal(tipoIva);
    tipoIvaTantoPor1 = tipoIvaTantoPor1.divide(new BigDecimal(100));
    iva = baseImponible.multiply(tipoIvaTantoPor1);
    iva = iva.with(rounding);
    principal = baseImponible.add(iva);
  }
}
