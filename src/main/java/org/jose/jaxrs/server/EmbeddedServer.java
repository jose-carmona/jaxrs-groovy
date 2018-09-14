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

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmbeddedServer {

	private int SERVER_PORT = 8680;
	private String URL = "http://localhost";
	private Server jettyServer;

	final Logger logger = LoggerFactory.getLogger(EmbeddedServer.class);

 	public EmbeddedServer() {
	}

	public void start() throws Throwable {

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    context.setContextPath("/");

    jettyServer = new Server(SERVER_PORT);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/calc/*");
    jerseyServlet.setInitOrder(0);

    jerseyServlet.setInitParameter("javax.ws.rs.Application", "org.jose.jaxrs.server.GroovyScriptServer");
 		jettyServer.start();
 	}

	public void stop() throws Throwable {
		jettyServer.stop();
		jettyServer.join();
	}
}
