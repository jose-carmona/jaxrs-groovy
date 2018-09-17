package org.jose.jaxrs.server;

import org.glassfish.jersey.server.ResourceConfig;

public class JaxrsServer extends ResourceConfig {

	public JaxrsServer() {
		packages("org.jose.jaxrs");
	}
}
