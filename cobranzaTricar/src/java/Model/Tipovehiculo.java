package Model;
// Generated 30/07/2015 11:43:35 AM by Hibernate Tools 4.3.1


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
 * Tipovehiculo generated by hbm2java
 */
@Entity
@Table(name="tipovehiculo"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Tipovehiculo  implements java.io.Serializable {


     private int idtipovehiculo;
     private String nombre;
     private Date fechareg;
     private Set vehiculos = new HashSet(0);

    public Tipovehiculo() {
    }

	
    public Tipovehiculo(int idtipovehiculo) {
        this.idtipovehiculo = idtipovehiculo;
    }
    public Tipovehiculo(int idtipovehiculo, String nombre, Date fechareg, Set vehiculos) {
       this.idtipovehiculo = idtipovehiculo;
       this.nombre = nombre;
       this.fechareg = fechareg;
       this.vehiculos = vehiculos;
    }
   
     @Id 

    
    @Column(name="idtipovehiculo", unique=true, nullable=false)
    public int getIdtipovehiculo() {
        return this.idtipovehiculo;
    }
    
    public void setIdtipovehiculo(int idtipovehiculo) {
        this.idtipovehiculo = idtipovehiculo;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="tipovehiculo")
    public Set getVehiculos() {
        return this.vehiculos;
    }
    
    public void setVehiculos(Set vehiculos) {
        this.vehiculos = vehiculos;
    }




}


