package cucumber.runtime.cdi;

import cucumber.api.java.ObjectFactory;
import cucumber.runtime.CucumberException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.spi.CDI;

/**
 * Object Factory con injección de dependincias en servidor
 * @autor jose_carmona
 *
 * La factoría de objetos tiene 2 modos de funcionamiento: cuando cucumber es
 * utilizado para test fuera de servidor de aplicaciones no usa injección de
 * dependencia y es una copia del cucumber.api.java.DefaultObjectFactory; cuando
 * es usado desde cucumber como un servicio de la apliación en el contenedor,
 * entonces usa CDI para injección de dependencias y es está basado en
 * cucumber.runtime.java.weld.WeldFactory que usa Weld SE.
 */
public class CDIObjectFactory implements ObjectFactory {

  private final Map<Class<?>, Object> instances = new HashMap<Class<?>, Object>();

  private CDI<Object> cdi;

  public CDIObjectFactory() {
    try {
      // accedemos a CDI vía clase estática en spi
      cdi = CDI.current();
    }
    catch (Exception err) {
      // no hay CDI, entonces no hay injección de dependiencias
    }
  }

  @Override
  public void start() {
    // nada que hacer. CDI es arrancado por el servidor EE
  }

  @Override
  public void stop() {
    // nada que hacer. CDI es manejado por el servidor EE
    // si no tenemos CDI, entonces limpiamos los objetos instanciados
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
    // si no tenemos CDI, buscanmos la instancia en la cache o la instanciamos
    if  (cdi==null) {
      T instance = type.cast(instances.get(type));
      if (instance == null) {
          instance = cacheNewInstance(type);
      }
      return instance;
    }
    // si tenemos CDI, pedimos la instacia de la clase a CDI
    return cdi.select(type).get();
  }

  // ver cucumber.api.java.DefaultObjectFactory
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
