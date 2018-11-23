package org.jose.jaxrs.model;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;

public class JsonLiq {

  private MonetaryAmount principal;
  public MonetaryAmount total;

  public MonetaryAmount getPrincipal() {
    return principal;
  }
  public void setPrincipal(MonetaryAmount principal) {
    this.principal = principal;
  }
}
