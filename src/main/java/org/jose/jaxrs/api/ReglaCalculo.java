package org.jose.jaxrs.api;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import org.jose.jaxrs.util.CodeVisitor;
import org.jose.jaxrs.model.Liquidacion;

public class ReglaCalculo {

  private String markdown;
  private String html;
  private String script;
  private String resultado;

  // r es la Liquidación resultado de la regla de cálculo
  private Liquidacion r;

  private GroovyScript groovyScript;

  // Constructor
  public ReglaCalculo() {
    groovyScript = new GroovyScript();
    r = new Liquidacion();
    groovyScript.setVariable("r", r);
  }

  // Getters / Setters
  public String getHtml() {
    return html;
  }

  public Liquidacion getLiqResultado() {
    return r;
  }

  public void setMarkdown( String markdown ) {
    this.markdown = markdown;

    Parser parser = Parser.builder().build();
    Node document = parser.parse(this.markdown);

    // estraemos el script en groovy
    CodeVisitor visitor = new CodeVisitor();
    document.accept(visitor);
    script = new String(visitor.code);
    groovyScript.setGroovyScript(script);

    HtmlRenderer renderer = HtmlRenderer.builder().build();
    html = renderer.render(document);
  }

  public String getResultado() {
    return groovyScript.getResultado();
  }

  public String getScript() {
    return script;
  }

  public void setVariable(String var, Object value) {
    groovyScript.setVariable(var, value);
  }

  // Métodos
  public void calcular() {
    groovyScript.run();
  }
}
