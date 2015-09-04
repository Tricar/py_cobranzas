package Model;
// Generated 03/09/2015 12:09:40 PM by Hibernate Tools 4.3.1


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
 * Pagos generated by hbm2java
 */
@Entity
@Table(name="pagos"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Pagos  implements java.io.Serializable {


     private int idpagos;
     private Letras letras;
     private String operacion;
     private BigDecimal monto;
     private Date fecreg;
     private String descripcion;

    public Pagos() {
    }

	
    public Pagos(int idpagos) {
        this.idpagos = idpagos;
    }
    public Pagos(int idpagos, Letras letras, String operacion, BigDecimal monto, Date fecreg, String descripcion) {
       this.idpagos = idpagos;
       this.letras = letras;
       this.operacion = operacion;
       this.monto = monto;
       this.fecreg = fecreg;
       this.descripcion = descripcion;
    }
   
     @Id 

    
    @Column(name="idpagos", unique=true, nullable=false)
    public int getIdpagos() {
        return this.idpagos;
    }
    
    public void setIdpagos(int idpagos) {
        this.idpagos = idpagos;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idletras")
    public Letras getLetras() {
        return this.letras;
    }
    
    public void setLetras(Letras letras) {
        this.letras = letras;
    }

    
    @Column(name="operacion", length=20)
    public String getOperacion() {
        return this.operacion;
    }
    
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    
    @Column(name="monto", precision=17, scale=5)
    public BigDecimal getMonto() {
        return this.monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecreg", length=23)
    public Date getFecreg() {
        return this.fecreg;
    }
    
    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

    
    @Column(name="descripcion", length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagos other = (Pagos) obj;
        if (this.idpagos != other.idpagos) {
            return false;
        }
        return true;
    }

    


}


