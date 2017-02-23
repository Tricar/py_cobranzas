package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tipooperacioncontable generated by hbm2java
 */
@Entity
@Table(name="tipooperacioncontable"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Tipooperacioncontable  implements java.io.Serializable {


     private int idtipooperacioncontable;
     private String descripcion;
     private String codigocontableto;
     private Set operacions = new HashSet(0);

    public Tipooperacioncontable() {
    }

	
    public Tipooperacioncontable(int idtipooperacioncontable) {
        this.idtipooperacioncontable = idtipooperacioncontable;
    }
    public Tipooperacioncontable(int idtipooperacioncontable, String descripcion, String codigocontableto, Set operacions) {
       this.idtipooperacioncontable = idtipooperacioncontable;
       this.descripcion = descripcion;
       this.codigocontableto = codigocontableto;
       this.operacions = operacions;
    }
   
     @Id 

    
    @Column(name="idtipooperacioncontable", unique=true, nullable=false)
    public int getIdtipooperacioncontable() {
        return this.idtipooperacioncontable;
    }
    
    public void setIdtipooperacioncontable(int idtipooperacioncontable) {
        this.idtipooperacioncontable = idtipooperacioncontable;
    }

    
    @Column(name="descripcion", length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="codigocontableto", length=50)
    public String getCodigocontableto() {
        return this.codigocontableto;
    }
    
    public void setCodigocontableto(String codigocontableto) {
        this.codigocontableto = codigocontableto;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tipooperacioncontable")
    public Set getOperacions() {
        return this.operacions;
    }
    
    public void setOperacions(Set operacions) {
        this.operacions = operacions;
    }




}


