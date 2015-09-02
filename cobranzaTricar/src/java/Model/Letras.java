package Model;
// Generated 31/08/2015 11:12:21 AM by Hibernate Tools 4.3.1


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
 * Letras generated by hbm2java
 */
@Entity
@Table(name="letras"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Letras  implements java.io.Serializable {


     private int idletras;
     private Credito idventa;
     private BigDecimal montoletra;
     private Date fecini;
     private Date fecven;
     private BigDecimal monto;
     private BigDecimal interes;
     private BigDecimal saldo;
     private Date fecreg;
     private String estado;
     private Set pagoses = new HashSet(0);

    public Letras() {
    }

	
    public Letras(int idletras) {
        this.idletras = idletras;
    }
    public Letras(int idletras, Credito idventa, BigDecimal montoletra, Date fecini, Date fecven, BigDecimal monto, BigDecimal interes, BigDecimal saldo, Date fecreg, String estado, Set pagoses) {
       this.idletras = idletras;
       this.idventa = idventa;
       this.montoletra = montoletra;
       this.fecini = fecini;
       this.fecven = fecven;
       this.monto = monto;
       this.interes = interes;
       this.saldo = saldo;
       this.fecreg = fecreg;
       this.estado = estado;
       this.pagoses = pagoses;
    }
   
     @Id 

    
    @Column(name="idletras", unique=true, nullable=false)
    public int getIdletras() {
        return this.idletras;
    }
    
    public void setIdletras(int idletras) {
        this.idletras = idletras;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idventa")
    public Credito getCredito() {
        return this.idventa;
    }
    
    public void setCredito(Credito idventa) {
        this.idventa = idventa;
    }

    
    @Column(name="montoletra", precision=17, scale=5)
    public BigDecimal getMontoletra() {
        return this.montoletra;
    }
    
    public void setMontoletra(BigDecimal montoletra) {
        this.montoletra = montoletra;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecini", length=23)
    public Date getFecini() {
        return this.fecini;
    }
    
    public void setFecini(Date fecini) {
        this.fecini = fecini;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecven", length=23)
    public Date getFecven() {
        return this.fecven;
    }
    
    public void setFecven(Date fecven) {
        this.fecven = fecven;
    }

    
    @Column(name="monto", precision=17, scale=5)
    public BigDecimal getMonto() {
        return this.monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    
    @Column(name="interes", precision=17, scale=5)
    public BigDecimal getInteres() {
        return this.interes;
    }
    
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    
    @Column(name="saldo", precision=17, scale=5)
    public BigDecimal getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecreg", length=23)
    public Date getFecreg() {
        return this.fecreg;
    }
    
    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

    
    @Column(name="estado", length=2)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="letras")
    public Set getPagoses() {
        return this.pagoses;
    }
    
    public void setPagoses(Set pagoses) {
        this.pagoses = pagoses;
    }

    


}


