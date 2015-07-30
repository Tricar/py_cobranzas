package Model;
// Generated 30/07/2015 04:02:17 PM by Hibernate Tools 4.3.1


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
     private String ruc;
     private String nombre;
     private String direccion;
     private String telefono;
     private String celular;
     private String ciudad;
     private Date fechareg;
     private Set ventas = new HashSet(0);

    public Empresa() {
    }

	
    public Empresa(int idempresa, String ruc) {
        this.idempresa = idempresa;
        this.ruc = ruc;
    }
    public Empresa(int idempresa, String ruc, String nombre, String direccion, String telefono, String celular, String ciudad, Date fechareg, Set ventas) {
       this.idempresa = idempresa;
       this.ruc = ruc;
       this.nombre = nombre;
       this.direccion = direccion;
       this.telefono = telefono;
       this.celular = celular;
       this.ciudad = ciudad;
       this.fechareg = fechareg;
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

    
    @Column(name="ruc", nullable=false, length=11)
    public String getRuc() {
        return this.ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    
    @Column(name="nombre", length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="direccion", length=20)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    @Column(name="telefono", length=9)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Column(name="celular", length=9)
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }

    
    @Column(name="ciudad", length=20)
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechareg", length=23)
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="empresa")
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }




}


