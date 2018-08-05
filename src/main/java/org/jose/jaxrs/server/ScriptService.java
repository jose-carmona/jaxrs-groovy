package org.jose.jaxrs.server;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.jose.jaxrs.api.GroovyScript;

import org.jose.jaxrs.model.Liquidacion;

@Path("/script")
public class ScriptService {

	@GET
	@Path("/test")
	public GroovyScript s1() {

		GroovyScript s1 = new GroovyScript("test");

		s1.setVariable( "foo", new Integer(2));
		s1.run();

		return s1;
	}

	@GET
	@Path("/liq")
	public Liquidacion liq() {

		Liquidacion resultado = new Liquidacion();

		GroovyScript calculo = new GroovyScript("calculo");

		calculo.setVariable("resultado", resultado);
		calculo.run();

		System.out.println(calculo.getResult());

		return resultado;
	}

}
