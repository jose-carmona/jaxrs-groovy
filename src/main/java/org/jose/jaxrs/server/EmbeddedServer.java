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
import org.eclipse.jetty.webapp.WebAppContext;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmbeddedServer {

	private int SERVER_PORT = 8680;
	private String URL = "http://localhost";

	final Logger logger = LoggerFactory.getLogger(EmbeddedServer.class);

 	public EmbeddedServer() {
	}

	public void start() throws Throwable {

 		URI baseUri = UriBuilder.fromUri(URL).port(SERVER_PORT).build();

 		// ResourceConfig config = new ResourceConfig(ScriptService.class);
		ResourceConfig config = new ResourceConfig().packages("").register(ScriptService.class);

 		Server server = JettyHttpContainerFactory.createServer(baseUri, config, false);

 		ContextHandler contextHandler = new ContextHandler("/calc");
 		contextHandler.setHandler(server.getHandler());

 		ProtectionDomain protectionDomain = EmbeddedServer.class.getProtectionDomain();

 		URL location = protectionDomain.getCodeSource().getLocation();

 		ResourceHandler resourceHandler = new ResourceHandler();
 		resourceHandler.setWelcomeFiles(new String[] { "index.html" });
 		resourceHandler.setResourceBase(location.toExternalForm());

 		logger.debug(location.toExternalForm());

 		HandlerCollection handlerCollection = new HandlerCollection();
 		handlerCollection.setHandlers(new Handler[] { contextHandler, resourceHandler, new DefaultHandler() });

		WebAppContext webapp = new WebAppContext(location.toExternalForm(), "/");
		server.setHandler(webapp);

 		// server.setHandler(handlerCollection);
 		server.start();
 	}
}
