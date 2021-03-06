package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Movcaja generated by hbm2java
 */
@Entity
@Table(name="movcaja"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Movcaja  implements java.io.Serializable {


     private Integer idmovcaja;
     private Caja caja;
     private BigDecimal monto;
     private String tipomov;
     private Date fechamov;
     private Conceptos concepto;
     private Letras origen;

    public Movcaja() {
    }

	
    public Movcaja(Integer idmovcaja) {
        this.idmovcaja = idmovcaja;
    }
    public Movcaja(Integer idmovcaja, Caja caja, BigDecimal monto, String tipomov, Date fechamov, Conceptos concepto, Letras origen) {
       this.idmovcaja = idmovcaja;
       this.caja = caja;
       this.monto = monto;
       this.tipomov = tipomov;
       this.fechamov = fechamov;
       this.concepto = concepto;
       this.origen = origen;
    }
   
     @Id 

    
    @Column(name="idmovcaja", unique=true, nullable=false)
    public Integer getIdmovcaja() {
        return this.idmovcaja;
    }
    
    public void setIdmovcaja(Integer idmovcaja) {
        this.idmovcaja = idmovcaja;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcaja")
    public Caja getCaja() {
        return this.caja;
    }
    
    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    
    @Column(name="monto", precision=17)
    public BigDecimal getMonto() {
        return this.monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    
    @Column(name="tipomov", length=2)
    public String getTipomov() {
        return this.tipomov;
    }
    
    public void setTipomov(String tipomov) {
        this.tipomov = tipomov;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechamov", length=16)
    public Date getFechamov() {
        return this.fechamov;
    }
    
    public void setFechamov(Date fechamov) {
        this.fechamov = fechamov;
    }

    
    @Column(name="anticipo")
    public Conceptos getConcepto() {
        return this.concepto;
    }
    
    public void setConcepto(Conceptos anticipo) {
        this.concepto = anticipo;
    }

    
    @Column(name="origen")
    public Letras getOrigen() {
        return this.origen;
    }
    
    public void setOrigen(Letras origen) {
        this.origen = origen;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idmovcaja != null)
                ? idmovcaja.equals(((Movcaja) other).idmovcaja)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idmovcaja != null)
                ? (getClass().hashCode() + idmovcaja.hashCode())
                : super.hashCode();
        }

}

