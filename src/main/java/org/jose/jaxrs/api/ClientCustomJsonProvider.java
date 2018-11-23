package org.jose.jaxrs.api;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.zalando.jackson.datatype.money.MoneyModule;


@Provider
public class ClientCustomJsonProvider implements ContextResolver<ObjectMapper> {
   private final ObjectMapper mapper;

   public ClientCustomJsonProvider() {
     mapper = new ObjectMapper().findAndRegisterModules();
   }

   @Override
   public ObjectMapper getContext(Class<?> type) {
       return mapper;
   }
}
