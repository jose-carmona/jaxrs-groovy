package cucumber.runtime.cdi;
import javax.enterprise.context.SessionScoped;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class WeldSeAdapter {

  private WeldContainer container;

  public void start() {
    Weld weld = new Weld();
    container = weld.initialize();
  }

  public WeldContainer current() {
    return container.current();
  }

  public void stop() {
    if (container.isRunning()) {
      container.close();
    }
  }
}
