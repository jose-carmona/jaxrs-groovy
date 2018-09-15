package org.jose.jaxrs.api;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import org.jose.jaxrs.util.CodeVisitor;


public class ReglaCalculo {

  private String markdown;
  private String html;
  private String script;
  private String resultado;

  private GroovyScript groovyScript;

  // Constructor
  public ReglaCalculo() {
    groovyScript = new GroovyScript();
  }

  // Getters / Setters
  public String getHtml() {
    return html;
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
