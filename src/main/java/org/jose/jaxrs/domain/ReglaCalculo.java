package org.jose.jaxrs.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/**
 * Regla de Cálcula
 */
@Entity
@Table(name = "LTR_REGLAS_CALCULO")
public class ReglaCalculo {

    @Id
    @GeneratedValue
    private long cdi_regla;
    private String ds_regla;

    public ReglaCalculo() {
    }

    public ReglaCalculo(String ds_regla) {
        this.ds_regla = ds_regla;
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

}

/*
@NamedQueries({
        @NamedQuery(name = "ReglaCalculo.getAll", query = "select r from ltr_reglas_calculo r")
})
*/
