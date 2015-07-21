/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranzas.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sistemas2
 */
@Entity
@Table(name = "anexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anexo.findAll", query = "SELECT a FROM Anexo a"),
    @NamedQuery(name = "Anexo.findByIdanexo", query = "SELECT a FROM Anexo a WHERE a.idanexo = :idanexo"),
    @NamedQuery(name = "Anexo.findByNombre", query = "SELECT a FROM Anexo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Anexo.findByDni", query = "SELECT a FROM Anexo a WHERE a.dni = :dni"),
    @NamedQuery(name = "Anexo.findByRuc", query = "SELECT a FROM Anexo a WHERE a.ruc = :ruc"),
    @NamedQuery(name = "Anexo.findByDireccion", query = "SELECT a FROM Anexo a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Anexo.findByTelefono", query = "SELECT a FROM Anexo a WHERE a.telefono = :telefono"),
    @NamedQuery(name = "Anexo.findByCelular", query = "SELECT a FROM Anexo a WHERE a.celular = :celular"),
    @NamedQuery(name = "Anexo.findByEdad", query = "SELECT a FROM Anexo a WHERE a.edad = :edad"),
    @NamedQuery(name = "Anexo.findByFechanac", query = "SELECT a FROM Anexo a WHERE a.fechanac = :fechanac"),
    @NamedQuery(name = "Anexo.findByFechareg", query = "SELECT a FROM Anexo a WHERE a.fechareg = :fechareg"),
    @NamedQuery(name = "Anexo.findBySexo", query = "SELECT a FROM Anexo a WHERE a.sexo = :sexo"),
    @NamedQuery(name = "Anexo.findByApepat", query = "SELECT a FROM Anexo a WHERE a.apepat = :apepat"),
    @NamedQuery(name = "Anexo.findByApemat", query = "SELECT a FROM Anexo a WHERE a.apemat = :apemat"),
    @NamedQuery(name = "Anexo.findByEmail", query = "SELECT a FROM Anexo a WHERE a.email = :email")})
public class Anexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idanexo")
    private Integer idanexo;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 8)
    @Column(name = "dni")
    private String dni;
    @Size(max = 11)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 9)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 9)
    @Column(name = "celular")
    private String celular;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "fechanac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanac;
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Size(max = 20)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 30)
    @Column(name = "apepat")
    private String apepat;
    @Size(max = 30)
    @Column(name = "apemat")
    private String apemat;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anexo")
    private List<Venta> ventaList;
    @JoinColumn(name = "idtipoanexo", referencedColumnName = "idtipoanexo")
    @ManyToOne(optional = false)
    private TipoAnexo idtipoanexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<Usuario> usuarioList;

    public Anexo() {
    }

    public Anexo(Integer idanexo) {
        this.idanexo = idanexo;
    }

    public Integer getIdanexo() {
        return idanexo;
    }

    public void setIdanexo(Integer idanexo) {
        this.idanexo = idanexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getApepat() {
        return apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    public String getApemat() {
        return apemat;
    }

    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public TipoAnexo getIdtipoanexo() {
        return idtipoanexo;
    }

    public void setIdtipoanexo(TipoAnexo idtipoanexo) {
        this.idtipoanexo = idtipoanexo;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idanexo != null ? idanexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anexo)) {
            return false;
        }
        Anexo other = (Anexo) object;
        if ((this.idanexo == null && other.idanexo != null) || (this.idanexo != null && !this.idanexo.equals(other.idanexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cobranzas.models.Anexo[ idanexo=" + idanexo + " ]";
    }
    
}
