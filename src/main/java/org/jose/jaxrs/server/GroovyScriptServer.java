package org.jose.jaxrs.server;

import org.glassfish.jersey.server.ResourceConfig;

public class GroovyScriptServer extends ResourceConfig {

	public GroovyScriptServer() {
		packages("org.jose.jaxrs");
	}
}
