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
 * Anexo generated by hbm2java
 */
@Entity
@Table(name="anexo"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Anexo  implements java.io.Serializable {


     private Integer idanexo;
     private Tipoanexo tipoanexo;
     private String nombre;
     private String dni;
     private String ruc;
     private String direccion;
     private String telefono;
     private String celular;
     private Integer edad;
     private Date fechanac;
     private Date fechareg;
     private String sexo;
     private String apepat;
     private String apemat;
     private String email;
     private String codven;
     private Set datoscreditosForAprobado = new HashSet(0);
     private Set datoscreditosForVerificador = new HashSet(0);
     private Set detcreditos = new HashSet(0);
     private Set ventasForCodven = new HashSet(0);
     private Set usuarios = new HashSet(0);
     private Set ventasForIdanexo = new HashSet(0);

    public Anexo() {
    }

	
    public Anexo(Integer idanexo, Tipoanexo tipoanexo) {
        this.idanexo = idanexo;
        this.tipoanexo = tipoanexo;
    }
    public Anexo(Integer idanexo, Tipoanexo tipoanexo, String nombre, String dni, String ruc, String direccion, String telefono, String celular, Integer edad, Date fechanac, Date fechareg, String sexo, String apepat, String apemat, String email, String codven, Set datoscreditosForAprobado, Set datoscreditosForVerificador, Set detcreditos, Set ventasForCodven, Set usuarios, Set ventasForIdanexo) {
       this.idanexo = idanexo;
       this.tipoanexo = tipoanexo;
       this.nombre = nombre;
       this.dni = dni;
       this.ruc = ruc;
       this.direccion = direccion;
       this.telefono = telefono;
       this.celular = celular;
       this.edad = edad;
       this.fechanac = fechanac;
       this.fechareg = fechareg;
       this.sexo = sexo;
       this.apepat = apepat;
       this.apemat = apemat;
       this.email = email;
       this.codven = codven;
       this.datoscreditosForAprobado = datoscreditosForAprobado;
       this.datoscreditosForVerificador = datoscreditosForVerificador;
       this.detcreditos = detcreditos;
       this.ventasForCodven = ventasForCodven;
       this.usuarios = usuarios;
       this.ventasForIdanexo = ventasForIdanexo;
    }
   
     @Id 

    
    @Column(name="idanexo", unique=true, nullable=false)
    public Integer getIdanexo() {
        return this.idanexo;
    }
    
    public void setIdanexo(Integer idanexo) {
        this.idanexo = idanexo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idtipoanexo", nullable=false)
    public Tipoanexo getTipoanexo() {
        return this.tipoanexo;
    }
    
    public void setTipoanexo(Tipoanexo tipoanexo) {
        this.tipoanexo = tipoanexo;
    }

    
    @Column(name="nombre", length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="dni", length=8)
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }

    
    @Column(name="ruc", length=11)
    public String getRuc() {
        return this.ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    
    @Column(name="direccion", length=50)
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

    
    @Column(name="edad")
    public Integer getEdad() {
        return this.edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechanac", length=23)
    public Date getFechanac() {
        return this.fechanac;
    }
    
    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechareg", length=23)
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    
    @Column(name="sexo", length=20)
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    
    @Column(name="apepat", length=30)
    public String getApepat() {
        return this.apepat;
    }
    
    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    
    @Column(name="apemat", length=30)
    public String getApemat() {
        return this.apemat;
    }
    
    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    
    @Column(name="email", length=20)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="codven", length=10)
    public String getCodven() {
        return this.codven;
    }
    
    public void setCodven(String codven) {
        this.codven = codven;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByAprobado")
    public Set getDatoscreditosForAprobado() {
        return this.datoscreditosForAprobado;
    }
    
    public void setDatoscreditosForAprobado(Set datoscreditosForAprobado) {
        this.datoscreditosForAprobado = datoscreditosForAprobado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByVerificador")
    public Set getDatoscreditosForVerificador() {
        return this.datoscreditosForVerificador;
    }
    
    public void setDatoscreditosForVerificador(Set datoscreditosForVerificador) {
        this.datoscreditosForVerificador = datoscreditosForVerificador;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexo")
    public Set getDetcreditos() {
        return this.detcreditos;
    }
    
    public void setDetcreditos(Set detcreditos) {
        this.detcreditos = detcreditos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByCodven")
    public Set getVentasForCodven() {
        return this.ventasForCodven;
    }
    
    public void setVentasForCodven(Set ventasForCodven) {
        this.ventasForCodven = ventasForCodven;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexo")
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByIdanexo")
    public Set getVentasForIdanexo() {
        return this.ventasForIdanexo;
    }
    
    public void setVentasForIdanexo(Set ventasForIdanexo) {
        this.ventasForIdanexo = ventasForIdanexo;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idanexo != null)
            ? idanexo.equals(((Anexo) other).idanexo)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (idanexo != null) 
            ? (getClass().hashCode() + idanexo.hashCode())
            : super.hashCode();
    }


}


