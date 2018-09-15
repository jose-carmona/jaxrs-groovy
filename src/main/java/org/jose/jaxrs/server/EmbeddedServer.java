package org.jose.jaxrs.server;

import java.net.URI;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.glassfish.jersey.servlet.ServletContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmbeddedServer {

	private int SERVER_PORT = 8680;
	private Server jettyServer;

	final Logger logger = LoggerFactory.getLogger(EmbeddedServer.class);

 	public EmbeddedServer() {
	}

	public URI getURI() {
		return jettyServer.getURI();
	}

	public void start() throws Throwable {
		logger.debug("start()");

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    context.setContextPath("/");

    jettyServer = new Server(SERVER_PORT);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/calc/*");
    jerseyServlet.setInitOrder(0);

    jerseyServlet.setInitParameter("javax.ws.rs.Application", "org.jose.jaxrs.server.GroovyScriptServer");
 		jettyServer.start();

		logger.debug("URI" + jettyServer.getURI());
 	}

	public void stop() throws Throwable {
		logger.debug("stop()");
		jettyServer.stop();
		jettyServer.join();
	}
}
