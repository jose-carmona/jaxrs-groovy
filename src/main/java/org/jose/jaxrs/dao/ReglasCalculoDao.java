package org.jose.jaxrs.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jose.jaxrs.domain.ReglaCalculo;

/**
 * DAO
 *
 * @author @jose_carmona
 */
@Stateless
public class ReglasCalculoDao {

  @PersistenceContext
  private EntityManager entityManager;

  public ReglaCalculo getByCdi_regla(long cdi_regla) {
    return entityManager.createNamedQuery("ReglaCalculo.getByCdi_regla", ReglaCalculo.class)
              .setParameter("cdi_regla", cdi_regla)
              .getSingleResult();
  }

  public ReglaCalculo getByCd_entidadCd_modelo(String cd_entidad, String cd_modelo) {
    return entityManager.createNamedQuery("ReglaCalculo.getByCd_entidadCd_modelo", ReglaCalculo.class)
              .setParameter("cd_entidad", cd_entidad)
              .setParameter("cd_modelo", cd_modelo)
              .getSingleResult();
  }
}
