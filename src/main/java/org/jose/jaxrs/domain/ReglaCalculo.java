package org.jose.jaxrs.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/**
 * Regla de Cálculo
 *
 * @author @jose_carmona
 */
@Entity
@Table(name = "LTR_REGLAS_CALCULO")
@NamedQueries({
  @NamedQuery(name = "ReglaCalculo.getByCdi_regla",
      query = "select r from ReglaCalculo r where r.cdi_regla = :cdi_regla"),
  @NamedQuery(name = "ReglaCalculo.getByCd_entidadCd_modelo",
      query = "select r from ReglaCalculo r inner join Publicacion p on p.cdi_regla = r.cdi_regla " +
              "where p.cd_entidad = :cd_entidad and p.cd_modelo = :cd_modelo")
})
public class ReglaCalculo {

  @Id
  private long cdi_regla;  // CDI
  private String ds_regla; // Descripción de la Regla de Cálculo
  private String md_regla; // Markdoown de la Regla de Cálculo

  public ReglaCalculo() {
  }

  public ReglaCalculo(long cdi_regla, String ds_regla, String md_regla) {
    this.cdi_regla = cdi_regla;
    this.ds_regla = ds_regla;
    this.md_regla = md_regla;
  }

  public long getCdi_regla() {
    return cdi_regla;
  }

  public void setCdi_regla(long cdi_regla) {
    this.cdi_regla = cdi_regla;
  }

  public String getDs_regla() {
    return ds_regla;
  }

  public void setDs_regla(String ds_regla) {
    this.ds_regla = ds_regla;
  }

  public String getMd_regla() {
    return md_regla;
  }

  public void setMd_regla(String md_regla) {
    this.md_regla = md_regla;
  }
}
