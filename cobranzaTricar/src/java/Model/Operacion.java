package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1

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
 * Operacion generated by hbm2java
 */
@Entity
@Table(name = "operacion",
         schema = "dbo",
         catalog = "cobranzas"
)
public class Operacion implements java.io.Serializable {

    private Integer idoperacion;
    private Anexo anexo;
    private Tipodocument tipodocument;
    private Integer idtipooperacioncontable;
    private Tipoventa tipoventa;
    private String codigo;
    private String recibo;
    private BigDecimal montototal;
    private Date created;
    private Integer idusuario;
    private Integer estado;
    private Set operaciondetalles = new HashSet(0);

    public Operacion() {
    }

    public Operacion(Integer idoperacion) {
        this.idoperacion = idoperacion;
    }

    public Operacion(Integer idoperacion, Anexo anexo, Tipodocument tipodocument, Integer idtipooperacioncontable, Tipoventa tipoventa, String codigo, String recibo, BigDecimal montototal, Date created, Integer idusuario, Integer estado, Set operaciondetalles) {
        this.idoperacion = idoperacion;
        this.anexo = anexo;
        this.tipodocument = tipodocument;
        this.idtipooperacioncontable = idtipooperacioncontable;
        this.tipoventa = tipoventa;
        this.codigo = codigo;
        this.recibo = recibo;
        this.montototal = montototal;
        this.created = created;
        this.idusuario = idusuario;
        this.estado = estado;
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
    @JoinColumn(name = "idanexo")
    public Anexo getAnexo() {
        return this.anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipodocument")
    public Tipodocument getTipodocument() {
        return this.tipodocument;
    }

    public void setTipodocument(Tipodocument tipodocument) {
        this.tipodocument = tipodocument;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipooperacioncontable")
    public Integer getIdtipooperacioncontable() {
        return this.idtipooperacioncontable;
    }

    public void setIdtipooperacioncontable(Integer idtipooperacioncontable) {
        this.idtipooperacioncontable = idtipooperacioncontable;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipoventa")
    public Tipoventa getTipoventa() {
        return this.tipoventa;
    }

    public void setTipoventa(Tipoventa tipoventa) {
        this.tipoventa = tipoventa;
    }

    @Column(name = "codigo", length = 20)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "recibo", length = 20)
    public String getRecibo() {
        return this.recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    @Column(name = "montototal", precision = 18)
    public BigDecimal getMontototal() {
        return this.montototal;
    }

    public void setMontototal(BigDecimal montototal) {
        this.montototal = montototal;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "idusuario")
    public Integer getIdusuario() {
        return this.idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    @Column(name = "estado")
    public Integer getEstado() {
        return this.estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operacion")
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
