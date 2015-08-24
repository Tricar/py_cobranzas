package Model;
// Generated 22/08/2015 12:24:13 PM by Hibernate Tools 4.3.1


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
 * Empresa generated by hbm2java
 */
@Entity
@Table(name="empresa"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Empresa  implements java.io.Serializable {


     private int idempresa;
     private String nombre;
     private Date fecreg;
     private Set ventas = new HashSet(0);

    public Empresa() {
    }

	
    public Empresa(int idempresa) {
        this.idempresa = idempresa;
    }
    public Empresa(int idempresa, String nombre, Date fecreg, Set ventas) {
       this.idempresa = idempresa;
       this.nombre = nombre;
       this.fecreg = fecreg;
       this.ventas = ventas;
    }
   
     @Id 

    
    @Column(name="idempresa", unique=true, nullable=false)
    public int getIdempresa() {
        return this.idempresa;
    }
    
    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    
    @Column(name="nombre", length=40)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecreg", length=23)
    public Date getFecreg() {
        return this.fecreg;
    }
    
    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="empresa")
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }




}


