package org.jose.jaxrs.api;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class CustomJsonProvider implements ContextResolver<ObjectMapper> {
   private final ObjectMapper mapper;

   public CustomJsonProvider() {
     System.out.println("CustomJsonProvider");
     mapper = new ObjectMapper();
     mapper.findAndRegisterModules();
   }

   @Override
   public ObjectMapper getContext(Class<?> type) {
       return mapper;
   }
}
