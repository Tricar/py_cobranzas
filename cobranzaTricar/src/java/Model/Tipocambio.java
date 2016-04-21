package Model;
// Generated 21/04/2016 10:03:10 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tipocambio generated by hbm2java
 */
@Entity
@Table(name = "tipocambio", schema = "dbo", catalog = "cobranzas"
)
public class Tipocambio implements java.io.Serializable {

    private Integer idtipocambio;
    private BigDecimal tipo;
    private Date fecreg;
    private Integer modelo;

    public Tipocambio() {
    }

    public Tipocambio(Integer idtipocambio) {
        this.idtipocambio = idtipocambio;
    }

    public Tipocambio(Integer idtipocambio, BigDecimal tipo, Date fecreg, Integer modelo) {
        this.idtipocambio = idtipocambio;
        this.tipo = tipo;
        this.fecreg = fecreg;
        this.modelo = modelo;
    }

    @Id

    @Column(name = "idtipocambio", unique = true, nullable = false)
    public Integer getIdtipocambio() {
        return this.idtipocambio;
    }

    public void setIdtipocambio(Integer idtipocambio) {
        this.idtipocambio = idtipocambio;
    }

    @Column(name = "tipo", precision = 17)
    public BigDecimal getTipo() {
        return this.tipo;
    }

    public void setTipo(BigDecimal tipo) {
        this.tipo = tipo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecreg", length = 23)
    public Date getFecreg() {
        return this.fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

    @Column(name = "modelo")
    public Integer getModelo() {
        return this.modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idtipocambio != null)
                ? idtipocambio.equals(((Tipocambio) other).idtipocambio)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idtipocambio != null)
                ? (getClass().hashCode() + idtipocambio.hashCode())
                : super.hashCode();
    }

}
