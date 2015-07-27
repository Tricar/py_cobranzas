package Model;
// Generated 27/07/2015 04:02:59 PM by Hibernate Tools 4.3.1


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
 * Color generated by hbm2java
 */
@Entity
@Table(name="color"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Color  implements java.io.Serializable {


     private int idcolor;
     private String color;
     private Date fecreg;
     private Set vehiculos = new HashSet(0);

    public Color() {
    }

	
    public Color(int idcolor, String color, Date fecreg) {
        this.idcolor = idcolor;
        this.color = color;
        this.fecreg = fecreg;
    }
    public Color(int idcolor, String color, Date fecreg, Set vehiculos) {
       this.idcolor = idcolor;
       this.color = color;
       this.fecreg = fecreg;
       this.vehiculos = vehiculos;
    }
   
     @Id 

    
    @Column(name="idcolor", unique=true, nullable=false)
    public int getIdcolor() {
        return this.idcolor;
    }
    
    public void setIdcolor(int idcolor) {
        this.idcolor = idcolor;
    }

    
    @Column(name="color", nullable=false, length=50)
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecreg", nullable=false, length=23)
    public Date getFecreg() {
        return this.fecreg;
    }
    
    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="color")
    public Set getVehiculos() {
        return this.vehiculos;
    }
    
    public void setVehiculos(Set vehiculos) {
        this.vehiculos = vehiculos;
    }




}


