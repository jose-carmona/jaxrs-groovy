package org.jose.jaxrs.api;

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

  private String script;
  private String result;

  private Binding binding;

  public GroovyScript( String script ){
    this.script = this.getFile(script);
    binding = new Binding();
  }

  private String getFile(String fileName) {

    StringBuilder result = new StringBuilder("");

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("/groovy/" + fileName + ".groovy").getFile());

    try (Scanner scanner = new Scanner(file)) {

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        result.append(line).append("\n");
      }

      scanner.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return result.toString();

  }

  public void setVariable( String var, Object value ) {
    binding.setVariable(var, value);
  }

  public String getResult() {
    return result;
  }

  public void run() {

    logger.debug("run()");

    try {

      binding.setVariable("foo", new Integer(2));

      GroovyShell shell = new GroovyShell(binding);

      Object value = shell.evaluate(this.script);

      this.result = value.toString();
    }
    catch (Exception err) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);

      err.printStackTrace(pw);
      this.result = sw.toString();
    }

  }

}
