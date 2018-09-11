package org.jose.jaxrs.server;

import java.net.URI;
import java.net.URL;
import java.security.ProtectionDomain;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public final class EmbeddedServer {

	private static final int SERVER_PORT = 8680;

	private EmbeddedServer() {
	}
}
