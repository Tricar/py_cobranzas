package Model;
// Generated 31/08/2015 11:12:21 AM by Hibernate Tools 4.3.1


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
 * Vehiculo generated by hbm2java
 */
@Entity
@Table(name="vehiculo"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Vehiculo  implements java.io.Serializable {


     private int idvehiculo;
     private Color color;
     private Modelo modelo;
     private String marca;
     private String serie;
     private Integer anofabri;
     private Date fechareg;
     private String motor;
     private String tipovehiculo;
     private Set creditos = new HashSet(0);

    public Vehiculo() {
    }

	
    public Vehiculo(int idvehiculo, Color color, Modelo modelo, String serie, String tipovehiculo) {
        this.idvehiculo = idvehiculo;
        this.color = color;
        this.modelo = modelo;
        this.serie = serie;
        this.tipovehiculo = tipovehiculo;
    }
    public Vehiculo(int idvehiculo, Color color, Modelo modelo, String marca, String serie, Integer anofabri, Date fechareg, String motor, String tipovehiculo, Set creditos) {
       this.idvehiculo = idvehiculo;
       this.color = color;
       this.modelo = modelo;
       this.marca = marca;
       this.serie = serie;
       this.anofabri = anofabri;
       this.fechareg = fechareg;
       this.motor = motor;
       this.tipovehiculo = tipovehiculo;
       this.creditos = creditos;
    }
   
     @Id 

    
    @Column(name="idvehiculo", unique=true, nullable=false)
    public int getIdvehiculo() {
        return this.idvehiculo;
    }
    
    public void setIdvehiculo(int idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcolor", nullable=false)
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmodelo", nullable=false)
    public Modelo getModelo() {
        return this.modelo;
    }
    
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    
    @Column(name="marca", length=20)
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    
    @Column(name="serie", nullable=false, length=20)
    public String getSerie() {
        return this.serie;
    }
    
    public void setSerie(String serie) {
        this.serie = serie;
    }

    
    @Column(name="anofabri")
    public Integer getAnofabri() {
        return this.anofabri;
    }
    
    public void setAnofabri(Integer anofabri) {
        this.anofabri = anofabri;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechareg", length=23)
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    
    @Column(name="motor", length=20)
    public String getMotor() {
        return this.motor;
    }
    
    public void setMotor(String motor) {
        this.motor = motor;
    }

    
    @Column(name="tipovehiculo", nullable=false, length=2)
    public String getTipovehiculo() {
        return this.tipovehiculo;
    }
    
    public void setTipovehiculo(String tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="vehiculo")
    public Set getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(Set creditos) {
        this.creditos = creditos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Vehiculo other = (Vehiculo) obj;
        if (this.idvehiculo != other.idvehiculo) {
            return false;
        }
        return true;
    }

    


}


