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
import javax.persistence.UniqueConstraint;

/**
 * Credito generated by hbm2java
 */
@Entity
@Table(name = "credito", schema = "dbo", catalog = "cobranzas", uniqueConstraints = @UniqueConstraint(columnNames = "liqventa")
)
public class Credito implements java.io.Serializable {

    private Integer idventa;
    private Anexo anexo;
    private Vehiculo vehiculo;
    private Anexo idaval;
    private String liqventa;
    private String condicionpago;
    private int nletras;
    private Anexo codven;
    private Date fechareg;
    private Integer aprobado;
    private Integer elaborado;
    private String tienda;
    private String empresa;
    private BigDecimal precio;
    private BigDecimal inicial;
    private BigDecimal saldo;
    private BigDecimal interes;
    private Anexo verificado;
    private Boolean cronograma;
    private Boolean contrato;
    private BigDecimal totaldeuda;
    private BigDecimal deudactual;
    private String estado;
    private String vehi;
    private Modelo modelo;
    private String adicional;
    private String guia;
    private String comprobante;
    private String comprobante2;
    private Set letrases = new HashSet(0);

    public Credito() {
    }

    public Credito(Integer idventa, Anexo anexo, String condicionpago, int nletras, Date fechareg, String tienda, BigDecimal precio, BigDecimal inicial, BigDecimal interes) {
        this.idventa = idventa;
        this.anexo = anexo;
        this.condicionpago = condicionpago;
        this.nletras = nletras;
        this.fechareg = fechareg;
        this.tienda = tienda;
        this.precio = precio;
        this.inicial = inicial;
        this.interes = interes;
    }

    public Credito(Integer idventa, Anexo anexo, Vehiculo vehiculo, Anexo idaval, String liqventa, String condicionpago, int nletras, Anexo codven, Date fechareg, Integer aprobado, Integer elaborado, String tienda, String empresa, BigDecimal precio, BigDecimal inicial, BigDecimal saldo, BigDecimal interes, Anexo verificado, Boolean cronograma, Boolean contrato, BigDecimal totaldeuda, BigDecimal deudactual, String estado, String vehi, Modelo modelo, String adicional, String guia, String comprobante, String comprobante2, Set letrases) {
        this.idventa = idventa;
        this.anexo = anexo;
        this.vehiculo = vehiculo;
        this.idaval = idaval;
        this.liqventa = liqventa;
        this.condicionpago = condicionpago;
        this.nletras = nletras;
        this.codven = codven;
        this.fechareg = fechareg;
        this.aprobado = aprobado;
        this.elaborado = elaborado;
        this.tienda = tienda;
        this.empresa = empresa;
        this.precio = precio;
        this.inicial = inicial;
        this.saldo = saldo;
        this.interes = interes;
        this.verificado = verificado;
        this.cronograma = cronograma;
        this.contrato = contrato;
        this.totaldeuda = totaldeuda;
        this.deudactual = deudactual;
        this.estado = estado;
        this.vehi = vehi;
        this.modelo = modelo;
        this.adicional = adicional;
        this.guia = guia;
        this.comprobante = comprobante;
        this.comprobante2 = comprobante2;
        this.letrases = letrases;
    }

    @Id

    @Column(name = "idventa", unique = true, nullable = false)
    public Integer getIdventa() {
        return this.idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
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
    @JoinColumn(name = "idvehiculo")
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idaval", nullable = false)
    public Anexo getIdaval() {
        return this.idaval;
    }

    public void setIdaval(Anexo idaval) {
        this.idaval = idaval;
    }

    @Column(name = "liqventa", unique = true, length = 15)
    public String getLiqventa() {
        return this.liqventa;
    }

    public void setLiqventa(String liqventa) {
        this.liqventa = liqventa;
    }

    @Column(name = "condicionpago", nullable = false, length = 2)
    public String getCondicionpago() {
        return this.condicionpago;
    }

    public void setCondicionpago(String condicionpago) {
        this.condicionpago = condicionpago;
    }

    @Column(name = "nletras", nullable = false)
    public int getNletras() {
        return this.nletras;
    }

    public void setNletras(int nletras) {
        this.nletras = nletras;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codven", nullable = false)
    public Anexo getCodven() {
        return this.codven;
    }

    public void setCodven(Anexo codven) {
        this.codven = codven;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechareg", nullable = false, length = 23)
    public Date getFechareg() {
        return this.fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }
    
    @Column(name = "aprobado")
    public Integer getAprobado() {
        return this.aprobado;
    }

    public void setAprobado(Integer aprobado) {
        this.aprobado = aprobado;
    }
    
    @Column(name = "elaborado")
    public Integer getElaborado() {
        return elaborado;
    }

    public void setElaborado(Integer elaborado) {
        this.elaborado = elaborado;
    }

    @Column(name = "tienda", nullable = false, length = 2)
    public String getTienda() {
        return this.tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    @Column(name = "empresa", length = 2)
    public String getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Column(name = "precio", nullable = false, precision = 17)
    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Column(name = "inicial", nullable = false, precision = 17)
    public BigDecimal getInicial() {
        return this.inicial;
    }

    public void setInicial(BigDecimal inicial) {
        this.inicial = inicial;
    }

    @Column(name = "saldo", precision = 17)
    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Column(name = "interes", nullable = false, precision = 17)
    public BigDecimal getInteres() {
        return this.interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verificado", nullable = false)
    public Anexo getVerificado() {
        return this.verificado;
    }

    public void setVerificado(Anexo verificado) {
        this.verificado = verificado;
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

    @Column(name = "totaldeuda", precision = 17)
    public BigDecimal getTotaldeuda() {
        return this.totaldeuda;
    }

    public void setTotaldeuda(BigDecimal totaldeuda) {
        this.totaldeuda = totaldeuda;
    }

    @Column(name = "deudactual", precision = 17)
    public BigDecimal getDeudactual() {
        return this.deudactual;
    }

    public void setDeudactual(BigDecimal deudactual) {
        this.deudactual = deudactual;
    }

    @Column(name = "estado", length = 2)
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "vehi", length = 3)
    public String getVehi() {
        return this.vehi;
    }

    public void setVehi(String vehi) {
        this.vehi = vehi;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmodelo", nullable = false)
    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Column(name = "adicional", length = 150)
    public String getAdicional() {
        return this.adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    @Column(name = "guia", length = 50)
    public String getGuia() {
        return this.guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    @Column(name = "comprobante", length = 50)
    public String getComprobante() {
        return this.comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    @Column(name = "comprobante2", length = 50)
    public String getComprobante2() {
        return this.comprobante2;
    }

    public void setComprobante2(String comprobante2) {
        this.comprobante2 = comprobante2;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "credito")
    public Set getLetrases() {
        return this.letrases;
    }

    public void setLetrases(Set letrases) {
        this.letrases = letrases;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idventa != null)
                ? idventa.equals(((Credito) other).idventa)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idventa != null)
                ? (getClass().hashCode() + idventa.hashCode())
                : super.hashCode();
    }

}
