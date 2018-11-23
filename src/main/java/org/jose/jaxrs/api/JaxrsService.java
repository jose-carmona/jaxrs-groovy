package org.jose.jaxrs.api;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javamoney.moneta.Money;

import org.jose.jaxrs.model.GroovyScript;
import org.jose.jaxrs.model.JsonLiq;
import org.jose.jaxrs.model.LiquidacionImpl;
import org.jose.jaxrs.model.Test;

@Path("/test")
public class JaxrsService {

	@GET
	@Path("/testMinGroovy")
	@Produces({MediaType.APPLICATION_JSON})
	public Test testMinGroovy() {

		GroovyScript s1 = new GroovyScript();

		s1.setVariable( "a", new Integer(2));
		s1.setVariable( "b", new Integer(2));
		s1.setScript( "a+b" );
		s1.calcular();

		Test r = new Test();
		r.test = s1.getResultado();

		return r;
	}

	@GET
	@Path("/testLiquidacion")
	@Produces({MediaType.APPLICATION_JSON})
	public JsonLiq testLiquidacion() {


		JsonLiq r = new JsonLiq();
		r.setPrincipal(Money.of(100.01, "EUR"));
		r.total = Money.of(29.95, "EUR");

		return r;
	}

}
