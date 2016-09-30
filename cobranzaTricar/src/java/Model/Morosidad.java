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
 * Morosidad generated by hbm2java
 */
@Entity
@Table(name = "morosidad", schema = "dbo", catalog = "cobranzas"
)
public class Morosidad implements java.io.Serializable {

    private Integer idmorosidad;    
    private Date fecreg;
    private BigDecimal total;
    private BigDecimal vencida;
    private BigDecimal mora;
    private String tienda;

    public Morosidad() {
    }

    public Morosidad(Integer idmorosidad) {
        this.idmorosidad = idmorosidad;
    }

    public Morosidad(Integer idmorosidad, Date fecreg, BigDecimal total, BigDecimal vencida, BigDecimal mora, String tienda) {
        this.idmorosidad = idmorosidad;
        this.fecreg = fecreg;
        this.total = total;
        this.vencida = vencida;
        this.mora = mora;
        this.tienda = tienda;
    }

    @Id

    @Column(name = "idmorosidad", unique = true, nullable = false)
    public Integer getIdtotalcambio() {
        return this.idmorosidad;
    }

    public void setIdtotalcambio(Integer idmorosidad) {
        this.idmorosidad = idmorosidad;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecreg", length = 23)
    public Date getFecreg() {
        return this.fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }
    
    @Column(name = "total", precision = 17)
    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    @Column(name = "vencida", precision = 17)
    public BigDecimal getVencida() {
        return this.vencida;
    }

    public void setVencida(BigDecimal vencida) {
        this.vencida = vencida;
    }
    
    @Column(name = "mora", precision = 17)
    public BigDecimal getMora() {
        return this.mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }
    
    @Column(name = "tienda", unique = true, length = 5)
    public String getTienda() {
        return this.tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }
    
    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idmorosidad != null)
                ? idmorosidad.equals(((Morosidad) other).idmorosidad)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idmorosidad != null)
                ? (getClass().hashCode() + idmorosidad.hashCode())
                : super.hashCode();
    }

}
