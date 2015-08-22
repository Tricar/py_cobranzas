package Model;
// Generated 21/08/2015 05:10:25 PM by Hibernate Tools 4.3.1


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
 * Modelo generated by hbm2java
 */
@Entity
@Table(name="modelo"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Modelo  implements java.io.Serializable {


     private Integer idmodelo;
     private String modelo;
     private Date fecreg;
     private Set vehiculos = new HashSet(0);

    public Modelo() {
    }

	
    public Modelo(Integer idmodelo) {
        this.idmodelo = idmodelo;
    }
    public Modelo(Integer idmodelo, String modelo, Date fecreg, Set vehiculos) {
       this.idmodelo = idmodelo;
       this.modelo = modelo;
       this.fecreg = fecreg;
       this.vehiculos = vehiculos;
    }
   
     @Id 

    
    @Column(name="idmodelo", unique=true, nullable=false)
    public Integer getIdmodelo() {
        return this.idmodelo;
    }
    
    public void setIdmodelo(Integer idmodelo) {
        this.idmodelo = idmodelo;
    }

    
    @Column(name="modelo", length=50)
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecreg", length=23)
    public Date getFecreg() {
        return this.fecreg;
    }
    
    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="modelo")
    public Set getVehiculos() {
        return this.vehiculos;
    }
    
    public void setVehiculos(Set vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idmodelo != null)
            ? idmodelo.equals(((Modelo) other).idmodelo)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (idmodelo != null) 
            ? (getClass().hashCode() + idmodelo.hashCode())
            : super.hashCode();
    }


}


