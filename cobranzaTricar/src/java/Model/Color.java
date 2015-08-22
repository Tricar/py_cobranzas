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
 * Color generated by hbm2java
 */
@Entity
@Table(name="color"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Color  implements java.io.Serializable {


     private Integer idcolor;
     private String color;
     private Date fecreg;
     private Set vehiculos = new HashSet(0);

    public Color() {
    }

	
    public Color(Integer idcolor, String color, Date fecreg) {
        this.idcolor = idcolor;
        this.color = color;
        this.fecreg = fecreg;
    }
    public Color(Integer idcolor, String color, Date fecreg, Set vehiculos) {
       this.idcolor = idcolor;
       this.color = color;
       this.fecreg = fecreg;
       this.vehiculos = vehiculos;
    }
   
     @Id 

    
    @Column(name="idcolor", unique=true, nullable=false)
    public Integer getIdcolor() {
        return this.idcolor;
    }
    
    public void setIdcolor(Integer idcolor) {
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

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idcolor != null)
            ? idcolor.equals(((Color) other).idcolor)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (idcolor != null) 
            ? (getClass().hashCode() + idcolor.hashCode())
            : super.hashCode();
    }


}


