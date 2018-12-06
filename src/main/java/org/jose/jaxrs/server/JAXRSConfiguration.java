package org.jose.jaxrs.server;

import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jose.jaxrs.api.CustomJsonProvider;
import org.jose.jaxrs.api.JaxrsTestService;

/**
 * Configures a JAX-RS endpoint
 *
 * @author jose
 */

@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

  @Override
  public Set<Class<?>> getClasses() {
      Set<Class<?>> classes = new HashSet<Class<?>>();

      classes.add(CustomJsonProvider.class);
      classes.add(JaxrsTestService.class);

      return classes;
  }

}
