package Model;
// Generated 24/07/2015 05:48:55 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Condicionpago generated by hbm2java
 */
@Entity
@Table(name="condicionpago"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Condicionpago  implements java.io.Serializable {


     private int idcondicionpago;
     private String nombre;
     private Date fechareg;
     private Set pagos = new HashSet(0);

    public Condicionpago() {
    }

	
    public Condicionpago(int idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }
    public Condicionpago(int idcondicionpago, String nombre, Date fechareg, Set pagos) {
       this.idcondicionpago = idcondicionpago;
       this.nombre = nombre;
       this.fechareg = fechareg;
       this.pagos = pagos;
    }
   
     @Id 

    
    @Column(name="idcondicionpago", unique=true, nullable=false)
    public int getIdcondicionpago() {
        return this.idcondicionpago;
    }
    
    public void setIdcondicionpago(int idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }

    
    @Column(name="nombre", length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechareg", length=23)
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="condicionpago")
    public Set getPagos() {
        return this.pagos;
    }
    
    public void setPagos(Set pagos) {
        this.pagos = pagos;
    }




}


