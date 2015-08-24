package Model;
// Generated 22/08/2015 12:24:13 PM by Hibernate Tools 4.3.1

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
 * Datoscredito generated by hbm2java
 */
@Entity
@Table(name = "datoscredito", schema = "dbo", catalog = "cobranzas"
)
public class Datoscredito implements java.io.Serializable {

    private Integer iddatoscredito;
    private Anexo anexoByAprobado;
    private Anexo anexoByVerificador;
    private Venta venta;
    private Integer nletras;
    private BigDecimal total;
    private BigDecimal interes;
    private BigDecimal tasainteres;
    private Boolean cronograma;
    private Boolean contrato;
    private Date fecreg;
    private Set letrases = new HashSet(0);
    private Set detcreditos = new HashSet(0);

    public Datoscredito() {
    }

    public Datoscredito(Integer iddatoscredito, Anexo anexoByAprobado, Anexo anexoByVerificador, Venta venta) {
        this.iddatoscredito = iddatoscredito;
        this.anexoByAprobado = anexoByAprobado;
        this.anexoByVerificador = anexoByVerificador;
        this.venta = venta;
    }

    public Datoscredito(Integer iddatoscredito, Anexo anexoByAprobado, Anexo anexoByVerificador, Venta venta, Integer nletras, BigDecimal total, BigDecimal interes, BigDecimal tasainteres, Boolean cronograma, Boolean contrato, Date fecreg, Set letrases, Set detcreditos) {
        this.iddatoscredito = iddatoscredito;
        this.anexoByAprobado = anexoByAprobado;
        this.anexoByVerificador = anexoByVerificador;
        this.venta = venta;
        this.nletras = nletras;
        this.total = total;
        this.interes = interes;
        this.tasainteres = tasainteres;
        this.cronograma = cronograma;
        this.contrato = contrato;
        this.fecreg = fecreg;
        this.letrases = letrases;
        this.detcreditos = detcreditos;
    }

    @Id

    @Column(name = "iddatoscredito", unique = true, nullable = false)
    public Integer getIddatoscredito() {
        return this.iddatoscredito;
    }

    public void setIddatoscredito(Integer iddatoscredito) {
        this.iddatoscredito = iddatoscredito;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprobado", nullable = false)
    public Anexo getAnexoByAprobado() {
        return this.anexoByAprobado;
    }

    public void setAnexoByAprobado(Anexo anexoByAprobado) {
        this.anexoByAprobado = anexoByAprobado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verificador", nullable = false)
    public Anexo getAnexoByVerificador() {
        return this.anexoByVerificador;
    }

    public void setAnexoByVerificador(Anexo anexoByVerificador) {
        this.anexoByVerificador = anexoByVerificador;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idventa", nullable = false)
    public Venta getVenta() {
        return this.venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Column(name = "nletras")
    public Integer getNletras() {
        return this.nletras;
    }

    public void setNletras(Integer nletras) {
        this.nletras = nletras;
    }

    @Column(name = "total", precision = 17, scale = 5)
    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Column(name = "interes", precision = 17, scale = 5)
    public BigDecimal getInteres() {
        return this.interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    @Column(name = "tasainteres", precision = 17, scale = 5)
    public BigDecimal getTasainteres() {
        return this.tasainteres;
    }

    public void setTasainteres(BigDecimal tasainteres) {
        this.tasainteres = tasainteres;
    }

    @Column(name = "cronograma")
    public Boolean getCronograma() {
        return this.cronograma;
    }

    public void setCronograma(Boolean cronograma) {
        this.cronograma = cronograma;
    }

    @Column(name = "contrato")
    public Boolean getContrato() {
        return this.contrato;
    }

    public void setContrato(Boolean contrato) {
        this.contrato = contrato;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecreg", length = 23)
    public Date getFecreg() {
        return this.fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "datoscredito")
    public Set getLetrases() {
        return this.letrases;
    }

    public void setLetrases(Set letrases) {
        this.letrases = letrases;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "datoscredito")
    public Set getDetcreditos() {
        return this.detcreditos;
    }

    public void setDetcreditos(Set detcreditos) {
        this.detcreditos = detcreditos;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && iddatoscredito != null)
                ? iddatoscredito.equals(((Datoscredito) other).iddatoscredito)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (iddatoscredito != null)
                ? (getClass().hashCode() + iddatoscredito.hashCode())
                : super.hashCode();
    }

}
