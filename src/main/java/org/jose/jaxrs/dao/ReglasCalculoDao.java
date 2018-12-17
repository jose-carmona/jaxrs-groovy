package org.jose.jaxrs.dao;

import org.jose.jaxrs.domain.ReglaCalculo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * DAO
 */
@Stateless
public class ReglasCalculoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ReglaCalculo> getAll() {
      System.out.println("ReglasCalculoDao.getAll()");
      return entityManager.createQuery("FROM ReglaCalculo", ReglaCalculo.class).getResultList();
      //return entityManager.createNamedQuery("ReglaCalculo.getAll", ReglaCalculo.class).getResultList();
    }
}
