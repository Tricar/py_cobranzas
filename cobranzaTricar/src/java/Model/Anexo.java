package Model;
// Generated 21/04/2016 10:03:10 AM by Hibernate Tools 4.3.1

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
@Table(name = "anexo", schema = "dbo", catalog = "cobranzas"
)
public class Anexo implements java.io.Serializable {

    private Integer idanexo;
    private String nombre;
    private String tipodocumento;
    private String numdocumento;
    private String direccion;
    private String aahh;
    private String cpm;
    private String distrito;
    private String sector;
    private String referencia;
    private String telefono;
    private String celular;
    private Integer edad;
    private Date fechanac;
    private Date fechareg;
    private Character sexo;
    private String apepat;
    private String apemat;
    private String email;
    private String tipoanexo;
    private String codven;
    private String estcivil;
    private String conyuge;
    private String dniconyu;
    private Set creditos = new HashSet(0);
    private Set usuarios = new HashSet(0);
    private Set ocupacions = new HashSet(0);
    private String nombres;

    public Anexo() {
    }

    public Anexo(Integer idanexo) {
        this.idanexo = idanexo;
    }

    public Anexo(Integer idanexo, String nombre, String tipodocumento, String numdocumento, String direccion, String aahh, String cpm, String distrito, String sector, String referencia, String telefono, String celular, Integer edad, Date fechanac, Date fechareg, Character sexo, String apepat, String apemat, String email, String tipoanexo, String codven, String estcivil, String conyuge, String dniconyu, Set creditos, Set usuarios, Set ocupacions) {
        this.idanexo = idanexo;
        this.nombre = nombre;
        this.tipodocumento = tipodocumento;
        this.numdocumento = numdocumento;
        this.direccion = direccion;
        this.aahh = aahh;
        this.cpm = cpm;
        this.distrito = distrito;
        this.sector = sector;
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
        this.creditos = creditos;
        this.usuarios = usuarios;
        this.ocupacions = ocupacions;
    }

    @Id

    @Column(name = "idanexo", unique = true, nullable = false)
    public Integer getIdanexo() {
        return this.idanexo;
    }

    public void setIdanexo(Integer idanexo) {
        this.idanexo = idanexo;
    }

    @Column(name = "nombre", length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "tipodocumento", length = 3)
    public String getTipodocumento() {
        return this.tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    @Column(name = "numdocumento", length = 11)
    public String getNumdocumento() {
        return this.numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    @Column(name = "direccion", length = 100)
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "aahh", length = 50)
    public String getAahh() {
        return this.aahh;
    }

    public void setAahh(String aahh) {
        this.aahh = aahh;
    }

    @Column(name = "cpm", length = 50)
    public String getCpm() {
        return this.cpm;
    }

    public void setCpm(String cpm) {
        this.cpm = cpm;
    }

    @Column(name = "distrito", length = 50)
    public String getDistrito() {
        return this.distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @Column(name = "sector", length = 50)
    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Column(name = "referencia", length = 200)
    public String getReferencia() {
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Column(name = "telefono", length = 10)
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "celular", length = 10)
    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Column(name = "edad")
    public Integer getEdad() {
        return this.edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechanac", length = 23)
    public Date getFechanac() {
        return this.fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechareg", length = 23)
    public Date getFechareg() {
        return this.fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    @Column(name = "sexo", length = 1)
    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    @Column(name = "apepat", length = 30)
    public String getApepat() {
        return this.apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    @Column(name = "apemat", length = 30)
    public String getApemat() {
        return this.apemat;
    }

    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    @Column(name = "email", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "tipoanexo", length = 2)
    public String getTipoanexo() {
        return this.tipoanexo;
    }

    public void setTipoanexo(String tipoanexo) {
        this.tipoanexo = tipoanexo;
    }

    @Column(name = "codven", length = 10)
    public String getCodven() {
        return this.codven;
    }

    public void setCodven(String codven) {
        this.codven = codven;
    }

    @Column(name = "estcivil", length = 2)
    public String getEstcivil() {
        return this.estcivil;
    }

    public void setEstcivil(String estcivil) {
        this.estcivil = estcivil;
    }

    @Column(name = "conyuge", length = 120)
    public String getConyuge() {
        return this.conyuge;
    }

    public void setConyuge(String conyuge) {
        this.conyuge = conyuge;
    }

    @Column(name = "dniconyu", length = 8)
    public String getDniconyu() {
        return this.dniconyu;
    }

    public void setDniconyu(String dniconyu) {
        this.dniconyu = dniconyu;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anexo")
    public Set getCreditos() {
        return this.creditos;
    }

    public void setCreditos(Set creditos) {
        this.creditos = creditos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anexo")
    public Set getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anexo")
    public Set getOcupacions() {
        return this.ocupacions;
    }

    public void setOcupacions(Set ocupacions) {
        this.ocupacions = ocupacions;
    }

    public String getNombres() {
        return nombres = nombre + " " + apepat + " " + apemat;
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
