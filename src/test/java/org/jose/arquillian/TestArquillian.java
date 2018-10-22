package org.jose.arquillian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestArquillian {

  final Logger logger = LoggerFactory.getLogger(TestArquillian.class);

  @Deployment
  public static JavaArchive createDeployment() {
    Logger logger = LoggerFactory.getLogger(TestArquillian.class);
    logger.debug("deploy");
    return ShrinkWrap.create(JavaArchive.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  public void test() {
    logger.debug("Parece que Arquillian está funcionando. Fuerzo un fallo");
    assertTrue (2 > 1);
  }
}
