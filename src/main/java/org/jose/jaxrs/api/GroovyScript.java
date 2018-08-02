package org.jose.jaxrs.api;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyScript {

  private String script;
  private String result;

  private Binding binding;

  public GroovyScript( String script ){
    this.script = script;
    binding = new Binding();
  }

  public String getResult() {
    return result;
  }

  public void run() {

    try {

      binding.setVariable("foo", new Integer(2));

      GroovyShell shell = new GroovyShell(binding);

      Object value = shell.evaluate(this.script);

      this.result = value.toString();
    }
    catch (Exception err) {
      this.result = err.toString();
    }

  }

}
