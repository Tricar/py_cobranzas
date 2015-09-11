package Model;
// Generated 03/09/2015 12:09:40 PM by Hibernate Tools 4.3.1


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
 * Anexo generated by hbm2java
 */
@Entity
@Table(name="anexo"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Anexo  implements java.io.Serializable {


     private Integer idanexo;
     private String nombre;
     private String dni;
     private String ruc;
     private String direccion;
     private String referencia;
     private String telefono;
     private String celular;
     private Integer edad;
     private Date fechanac;
     private Date fechareg;
     private String sexo;
     private String apepat;
     private String apemat;
     private String email;
     private String tipoanexo;
     private String codven;
     private String estcivil;
     private String conyuge;
     private String dniconyu;
     private Set creditosForIdaval = new HashSet(0);
     private Set creditosForCodven = new HashSet(0);
     private Set usuarios = new HashSet(0);
     private Set creditosForVerificado = new HashSet(0);
     private Set creditosForIdanexo = new HashSet(0);

    public Anexo() {
    }

	
    public Anexo(Integer idanexo, String tipoanexo) {
        this.idanexo = idanexo;
        this.tipoanexo = tipoanexo;
    }
    public Anexo(Integer idanexo, String nombre, String dni, String ruc, String direccion, String referencia, String telefono, String celular, Integer edad, Date fechanac, Date fechareg, String sexo, String apepat, String apemat, String email, String tipoanexo, String codven, String estcivil, String conyuge, String dniconyu, Set creditosForIdaval, Set creditosForCodven, Set usuarios, Set creditosForVerificado, Set creditosForIdanexo) {
       this.idanexo = idanexo;
       this.nombre = nombre;
       this.dni = dni;
       this.ruc = ruc;
       this.direccion = direccion;
       this.referencia = referencia;
       this.telefono = telefono;
       this.celular = celular;
       this.edad = edad;
       this.fechanac = fechanac;
       this.fechareg = fechareg;
       this.sexo = sexo;
       this.apepat = apepat;
       this.apemat = apemat;
       this.email = email;
       this.tipoanexo = tipoanexo;
       this.codven = codven;
       this.estcivil = estcivil;
       this.conyuge = conyuge;
       this.dniconyu = dniconyu;
       this.creditosForIdaval = creditosForIdaval;
       this.creditosForCodven = creditosForCodven;
       this.usuarios = usuarios;
       this.creditosForVerificado = creditosForVerificado;
       this.creditosForIdanexo = creditosForIdanexo;
    }
   
     @Id 

    
    @Column(name="idanexo", unique=true, nullable=false)
    public Integer getIdanexo() {
        return this.idanexo;
    }
    
    public void setIdanexo(Integer idanexo) {
        this.idanexo = idanexo;
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

    
    @Column(name="referencia", length=80)
    public String getReferencia() {
        return this.referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    
    @Column(name="sexo", length=1)
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

    
    @Column(name="tipoanexo", nullable=false, length=2)
    public String getTipoanexo() {
        return this.tipoanexo;
    }
    
    public void setTipoanexo(String tipoanexo) {
        this.tipoanexo = tipoanexo;
    }

    
    @Column(name="codven", length=10)
    public String getCodven() {
        return this.codven;
    }
    
    public void setCodven(String codven) {
        this.codven = codven;
    }

    
    @Column(name="estcivil", length=2)
    public String getEstcivil() {
        return this.estcivil;
    }
    
    public void setEstcivil(String estcivil) {
        this.estcivil = estcivil;
    }

    
    @Column(name="conyuge", length=120)
    public String getConyuge() {
        return this.conyuge;
    }
    
    public void setConyuge(String conyuge) {
        this.conyuge = conyuge;
    }

    
    @Column(name="dniconyu", length=8)
    public String getDniconyu() {
        return this.dniconyu;
    }
    
    public void setDniconyu(String dniconyu) {
        this.dniconyu = dniconyu;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByIdaval")
    public Set getCreditosForIdaval() {
        return this.creditosForIdaval;
    }
    
    public void setCreditosForIdaval(Set creditosForIdaval) {
        this.creditosForIdaval = creditosForIdaval;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByCodven")
    public Set getCreditosForCodven() {
        return this.creditosForCodven;
    }
    
    public void setCreditosForCodven(Set creditosForCodven) {
        this.creditosForCodven = creditosForCodven;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexo")
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByVerificado")
    public Set getCreditosForVerificado() {
        return this.creditosForVerificado;
    }
    
    public void setCreditosForVerificado(Set creditosForVerificado) {
        this.creditosForVerificado = creditosForVerificado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anexoByIdanexo")
    public Set getCreditosForIdanexo() {
        return this.creditosForIdanexo;
    }
    
    public void setCreditosForIdanexo(Set creditosForIdanexo) {
        this.creditosForIdanexo = creditosForIdanexo;
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


