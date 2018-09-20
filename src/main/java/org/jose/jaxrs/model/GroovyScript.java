package org.jose.jaxrs.model;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroovyScript {

  final Logger logger = LoggerFactory.getLogger(GroovyScript.class);

  private String scriptGlobal;
  private String script;
  private String result;

  private Binding binding;

  public GroovyScript() {
    logger.debug("constructor");
    binding = new Binding();
  }

  public String getScript() {
    return script;
  }

  public void setScript( String script ) {
    this.script = script;
  }

  public void setScriptGlobal( String script ) {
    scriptGlobal = script;
  }

  public void setVariable( String var, Object value ) {
    binding.setVariable(var, value);
  }

  public String getResultado() {
    return result;
  }

  public void calcular() {

    String scr = scriptGlobal == null ? script : scriptGlobal + "\n" + script;

    logger.debug("calcular()");

    try {
      GroovyShell shell = new GroovyShell(binding);
      Object value = shell.evaluate(scr);
      result = value == null ? null : value.toString();
    }
    catch (Exception err) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);

      err.printStackTrace(pw);
      this.result = sw.toString();
      logger.debug("ERROR: " + sw.toString());
      logger.debug(scr);
    }
  }

}
