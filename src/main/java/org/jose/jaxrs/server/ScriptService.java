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
		GroovyScript s1 = new GroovyScript();
		s1.setResult("OK");
		return s1;
	}

}
