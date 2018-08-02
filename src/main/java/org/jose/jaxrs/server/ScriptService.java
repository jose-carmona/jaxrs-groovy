package org.jose.jaxrs.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.jose.jaxrs.api.GroovyScript;

@Path("/script")
public class ScriptService {

	@GET
	@Path("/s1")
	public GroovyScript s1() {

		GroovyScript s1 = new GroovyScript("foo + 10");

		s1.run();

		return s1;
	}

	@GET
	@Path("/s2")
	public GroovyScript s2() {

		GroovyScript s2 = new GroovyScript("foo + 100");

		s2.run();

		return s2;
	}

}
