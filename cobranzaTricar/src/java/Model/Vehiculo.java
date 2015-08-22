package Model;
// Generated 21/08/2015 05:10:25 PM by Hibernate Tools 4.3.1


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


     private Integer idvehiculo;
     private Color color;
     private Modelo modelo;
     private Tipovehiculo tipovehiculo;
     private String marca;
     private String serie;
     private Integer anofabri;
     private Date fechareg;
     private String motor;
     private Set ventas = new HashSet(0);

    public Vehiculo() {
    }

	
    public Vehiculo(Integer idvehiculo, Color color, Modelo modelo, Tipovehiculo tipovehiculo, String serie) {
        this.idvehiculo = idvehiculo;
        this.color = color;
        this.modelo = modelo;
        this.tipovehiculo = tipovehiculo;
        this.serie = serie;
    }
    public Vehiculo(Integer idvehiculo, Color color, Modelo modelo, Tipovehiculo tipovehiculo, String marca, String serie, Integer anofabri, Date fechareg, String motor, Set ventas) {
       this.idvehiculo = idvehiculo;
       this.color = color;
       this.modelo = modelo;
       this.tipovehiculo = tipovehiculo;
       this.marca = marca;
       this.serie = serie;
       this.anofabri = anofabri;
       this.fechareg = fechareg;
       this.motor = motor;
       this.ventas = ventas;
    }
   
     @Id 

    
    @Column(name="idvehiculo", unique=true, nullable=false)
    public Integer getIdvehiculo() {
        return this.idvehiculo;
    }
    
    public void setIdvehiculo(Integer idvehiculo) {
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idtipovehiculo", nullable=false)
    public Tipovehiculo getTipovehiculo() {
        return this.tipovehiculo;
    }
    
    public void setTipovehiculo(Tipovehiculo tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="vehiculo")
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idvehiculo != null)
            ? idvehiculo.equals(((Vehiculo) other).idvehiculo)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (idvehiculo != null) 
            ? (getClass().hashCode() + idvehiculo.hashCode())
            : super.hashCode();
    }


}


