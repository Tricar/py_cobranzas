package Model;
// Generated 03/09/2015 12:09:40 PM by Hibernate Tools 4.3.1

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

    public Tipocambio() {
    }

    public Tipocambio(Integer idtipocambio, BigDecimal tipo, Date fecreg) {
        this.idtipocambio = idtipocambio;
        this.tipo = tipo;
        this.fecreg = fecreg;
    }    

    @Id

    @Column(name = "idtipocambio", unique = true, nullable = false)
    public Integer getIdtipocambio() {
        return this.idtipocambio;
    }

    public void setIdtipocambio(Integer idtipocambio) {
        this.idtipocambio = idtipocambio;
    }

    @Column(name = "tipo", nullable = false, precision = 17, scale = 2)
    public BigDecimal getTipo() {
        return this.tipo;
    }

    public void setTipo(BigDecimal tipo) {
        this.tipo = tipo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecreg", nullable = false, length = 23)
    public Date getFecreg() {
        return this.fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
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