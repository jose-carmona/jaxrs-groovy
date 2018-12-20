package org.jose.jaxrs.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/**
 * Pubicación de una Regla de Cálculo
 *
 * @author @jose_carmona
 */
@Entity
@Table(name = "LTR_PUBLICACIONES")
public class Publicacion {

  @Id
  @GeneratedValue
  private long cdi_publicacion;  // CDI
  private String cd_entidad; // Código de Entidad
  private String cd_modelo; // Código de Modelo
  // ¿Fecha de Publicación?
  private long cdi_regla; // Regla de Cálculo Publicada

  public Publicacion() {}

  public Publicacion(String cd_entidad, String cd_modelo, long cdi_regla) {
    this.cd_entidad = cd_entidad;
    this.cd_modelo = cd_modelo;
    this.cdi_regla = cdi_regla;
  }

  public long getCdi_publicacion() {
    return cdi_publicacion;
  }

  public void setCdi_publicacion(long cdi_publicacion) {
    this.cdi_publicacion = cdi_publicacion;
  }

  public String getCd_entidad() {
    return cd_entidad;
  }

  public void setCd_entidad(String cd_entidad) {
    this.cd_entidad = cd_entidad;
  }

  public String getCd_modelo() {
    return cd_modelo;
  }

  public void setCd_modelo(String cd_modelo) {
    this.cd_modelo = cd_modelo;
  }

  public long getCdi_regla() {
    return cdi_regla;
  }

  public void setCdi_regla(long cdi_regla) {
    this.cdi_regla = cdi_regla;
  }
}
