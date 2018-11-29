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
import org.jose.jaxrs.model.Liquidacion;
import org.jose.jaxrs.model.LiquidacionImpl;
import org.jose.jaxrs.model.Test;
import org.jose.jaxrs.model.ReglaCalculoMarkdown;

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
	public Liquidacion testLiquidacion() {

		String textoReglaCalculo = "```" + System.lineSeparator() +
			"r.c << [ \"Concepto 1\" : 10.01]" + System.lineSeparator() +
			"r.c << [ \"Concepto 2\" : 20.00]" + System.lineSeparator() +
			"r.c << [ \"Concepto 3\" : 70.00]" + System.lineSeparator() +
			"r.setBaseImponible(r.c.collect().value.sum())" + System.lineSeparator() +
			"r.setTipoIva(10)" + System.lineSeparator() +
			"r.aplicarIva()" + System.lineSeparator() +
			"```" + System.lineSeparator();

		ReglaCalculoMarkdown regla = new ReglaCalculoMarkdown();
		regla.setMarkdown(textoReglaCalculo);
		regla.calcular();

		Liquidacion r = regla.getLiqResultado();

		return r;
	}

}
