package Model;
// Generated 21/04/2016 10:03:10 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Vehiculo generated by hbm2java
 */
@Entity
@Table(name = "operacion", schema = "dbo", catalog = "cobranzas"
)
public class Operacion implements java.io.Serializable {

    private Integer idoperacion;
    private Anexo anexo;
    private Tipoventa tipoventa;    
    private Tipodocument tipodocument;
    private Integer idtipooperacioncontable;
    private String codigo;    
    private BigDecimal montototal;
    private Date created;
    private Set operaciondetalles = new HashSet(0);

    public Operacion() {
    }

    public Operacion(Integer idoperacion) {
        this.idoperacion = idoperacion;
    }

    public Operacion(Integer idoperacion, Anexo anexo, Tipoventa tipoventa, Tipodocument tipodocument, Integer idtipooperacioncontable, String codigo, BigDecimal montototal, Date created, Set operaciondetalles) {
        this.idoperacion = idoperacion;
        this.anexo = anexo;
        this.tipoventa = tipoventa;
        this.tipodocument = tipodocument;
        this.idtipooperacioncontable = idtipooperacioncontable;
        this.codigo = codigo;
        this.montototal = montototal;
        this.created = created;
        this.operaciondetalles = operaciondetalles;
    }

    @Id

    @Column(name = "idoperacion", unique = true, nullable = false)
    public Integer getIdoperacion() {
        return this.idoperacion;
    }

    public void setIdoperacion(Integer idoperacion) {
        this.idoperacion = idoperacion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idanexo", nullable = false)
    public Anexo getAnexo() {
        return this.anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipoventa", nullable = false)
    public Tipoventa getTipoventa() {
        return this.tipoventa;
    }

    public void setTipoventa(Tipoventa tipoventa) {
        this.tipoventa = tipoventa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipodocument", nullable = false)
    public Tipodocument getTipodocument() {
        return this.tipodocument;
    }

    public void setTipodocument(Tipodocument tipodocument) {
        this.tipodocument = tipodocument;
    }

    @Column(name = "idtipooperacioncontable")
    public Integer getIdtipooperacioncontable() {
        return this.idtipooperacioncontable;
    }

    public void setIdtipooperacioncontable(Integer idtipooperacioncontable) {
        this.idtipooperacioncontable = idtipooperacioncontable;
    }

    @Column(name = "codigo", nullable = false, length = 10)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "montototal", nullable = false, precision = 18)
    public BigDecimal getMontototal() {
        return this.montototal;
    }

    public void setMontototal(BigDecimal montototal) {
        this.montototal = montototal;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Operacion")
    public Set getOperaciondetalles() {
        return this.operaciondetalles;
    }

    public void setOperaciondetalles(Set operaciondetalles) {
        this.operaciondetalles = operaciondetalles;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idoperacion != null)
                ? idoperacion.equals(((Operacion) other).idoperacion)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idoperacion != null)
                ? (getClass().hashCode() + idoperacion.hashCode())
                : super.hashCode();
    }

}
