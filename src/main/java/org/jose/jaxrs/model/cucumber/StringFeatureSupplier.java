package org.jose.jaxrs.model.cucumber;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.TokenMatcher;
import gherkin.ast.GherkinDocument;

import cucumber.runtime.FeatureSupplier;
import cucumber.runtime.model.CucumberFeature;

import java.util.List;
import java.util.ArrayList;

public class StringFeatureSupplier implements FeatureSupplier {
  private final ArrayList<CucumberFeature> features;

  public StringFeatureSupplier() {
    features = new ArrayList<CucumberFeature>();
  }

  public void addStringFeature(String gherkin) throws ParserException {
    Parser<GherkinDocument> parser = new Parser<GherkinDocument>(new AstBuilder());
    TokenMatcher matcher = new TokenMatcher();

    GherkinDocument gherkinDocument = parser.parse(gherkin, matcher);
    String uri = "test" + features.size();
    CucumberFeature feature = new CucumberFeature(gherkinDocument, uri, gherkin);
    features.add(feature);
  }

  @Override
  public List<CucumberFeature> get() {
    return features;
  }
}
