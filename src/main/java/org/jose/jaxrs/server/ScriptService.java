package org.jose.jaxrs.server;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jose.jaxrs.api.GroovyScript;

import org.jose.jaxrs.model.Liquidacion;
import org.jose.jaxrs.model.Test;


@Path("/script")
public class ScriptService {

	@GET
	@Path("/test")
	@Produces({MediaType.APPLICATION_JSON})
	public Test s1() {

		GroovyScript s1 = new GroovyScript();

		s1.setVariable( "a", new Integer(2));
		s1.setVariable( "b", new Integer(2));
		s1.setGroovyScript( "a+b" );
		s1.run();

		Test r = new Test();
		r.test = s1.getResultado();

		return r;
	}

	@GET
	@Path("/liq")
	public Liquidacion liq() {

		Liquidacion resultado = new Liquidacion();

		GroovyScript calculo = new GroovyScript();

		calculo.setVariable("resultado", resultado);
		calculo.run();

		System.out.println(calculo.getResultado());

		return resultado;
	}

}
