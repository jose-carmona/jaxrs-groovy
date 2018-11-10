package org.jose.jaxrs.model.cucumber;

import cucumber.runtime.FeatureSupplier;
import cucumber.runtime.Runtime;

public class RuntimeAdapter{

  public byte exitStatus;

  public void test(FeatureSupplier features) {
    final Runtime runtime = Runtime.builder()
                    .withFeatureSupplier(features)
                    .withArg("--glue org.jose.jaxrs.model.cucumber.steps")
                    .build();
    runtime.run();
    exitStatus = runtime.exitStatus();
  }
}
