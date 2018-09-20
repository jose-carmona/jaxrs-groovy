package org.jose.jaxrs.model;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import org.jose.jaxrs.util.CodeVisitor;
import org.jose.jaxrs.model.LiquidacionImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReglaCalculoMarkdown extends GroovyScript implements ReglaCalculo {

  final Logger logger = LoggerFactory.getLogger(ReglaCalculoMarkdown.class);

  private String markdownGlobal;
  private String markdown;
  private String htmlGlobal;
  private String html;
  private String resultado;

  // r es la Liquidación resultado de la regla de cálculo
  private Liquidacion r;

  // Constructor
  public ReglaCalculoMarkdown() {
    super();
    r = new LiquidacionImpl();
    setVariable("r", r);
  }

  // Getters / Setters
  public String getHtml() {
    return html;
  }

  public Liquidacion getLiqResultado() {
    return r;
  }

  public void setMarkdownGlobal( String markdown ) {
    markdownGlobal = markdown;

    Parser parser = Parser.builder().build();
    Node document = parser.parse(markdownGlobal);

    // estraemos el script en groovy
    CodeVisitor visitor = new CodeVisitor();
    document.accept(visitor);
    setScriptGlobal(new String(visitor.code));

    HtmlRenderer renderer = HtmlRenderer.builder().build();
    htmlGlobal = renderer.render(document);
  }

  public void setMarkdown( String markdown ) {
    this.markdown = markdown;

    Parser parser = Parser.builder().build();
    Node document = parser.parse(this.markdown);

    // estraemos el script en groovy
    CodeVisitor visitor = new CodeVisitor();
    document.accept(visitor);
    setScript(new String(visitor.code));

    HtmlRenderer renderer = HtmlRenderer.builder().build();
    html = renderer.render(document);
  }

}
