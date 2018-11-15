package cucumber.runtime.cdi;

import cucumber.api.java.ObjectFactory;
import cucumber.runtime.CucumberException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.spi.CDI;

public class CDIObjectFactory implements ObjectFactory {

  private final Map<Class<?>, Object> instances = new HashMap<Class<?>, Object>();

  // accedemos a CDI vía clase estática en spi
  private CDI<Object> cdi;

  public CDIObjectFactory() {
    try {
      cdi = CDI.current();
    }
    catch (Exception err) {
    }
  }

  @Override
  public void start() {
    // nada que hacer. CDI es arrancado por el servidor EE
  }

  @Override
  public void stop() {
    // nada que hacer. CDI es manejado por el servidor EE
    if (cdi==null) {
      instances.clear();
    }
  }

  @Override
  public boolean addClass(final Class<?> type) {
    // nada que hacer. CDi en el servidor ya inspecciona en el deploy
    return true;
  }

  @Override
  public <T> T getInstance(final Class<T> type) {
    if  (cdi==null) {
      T instance = type.cast(instances.get(type));
      if (instance == null) {
          instance = cacheNewInstance(type);
      }
      return instance;
    }
    return cdi.select(type).get();
  }

  private <T> T cacheNewInstance(Class<T> type) {
      try {
          Constructor<T> constructor = type.getConstructor();
          T instance = constructor.newInstance();
          instances.put(type, instance);
          return instance;
      } catch (NoSuchMethodException e) {
          throw new CucumberException(String.format("%s doesn't have an empty constructor. If you need DI, put cucumber-picocontainer on the classpath", type), e);
      } catch (Exception e) {
          throw new CucumberException(String.format("Failed to instantiate %s", type), e);
      }
  }

}
