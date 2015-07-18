/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityDB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByIdvehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idvehiculo = :idvehiculo"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Vehiculo.findBySerie", query = "SELECT v FROM Vehiculo v WHERE v.serie = :serie"),
    @NamedQuery(name = "Vehiculo.findByColor", query = "SELECT v FROM Vehiculo v WHERE v.color = :color"),
    @NamedQuery(name = "Vehiculo.findByAnofabri", query = "SELECT v FROM Vehiculo v WHERE v.anofabri = :anofabri"),
    @NamedQuery(name = "Vehiculo.findByFechareg", query = "SELECT v FROM Vehiculo v WHERE v.fechareg = :fechareg"),
    @NamedQuery(name = "Vehiculo.findByMotor", query = "SELECT v FROM Vehiculo v WHERE v.motor = :motor"),
    @NamedQuery(name = "Vehiculo.findByStock", query = "SELECT v FROM Vehiculo v WHERE v.stock = :stock"),
    @NamedQuery(name = "Vehiculo.findByPrecio", query = "SELECT v FROM Vehiculo v WHERE v.precio = :precio")})
public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idvehiculo")
    private Integer idvehiculo;
    @Size(max = 20)
    @Column(name = "marca")
    private String marca;
    @Size(max = 20)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 20)
    @Column(name = "serie")
    private String serie;
    @Size(max = 20)
    @Column(name = "color")
    private String color;
    @Column(name = "anofabri")
    private Integer anofabri;
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Size(max = 20)
    @Column(name = "motor")
    private String motor;
    @Column(name = "stock")
    private Integer stock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    private Collection<Venta> ventaCollection;
    @JoinColumn(name = "idtipovehiculo", referencedColumnName = "idtipovehiculo")
    @ManyToOne(optional = false)
    private TipoVehiculo idtipovehiculo;

    public Vehiculo() {
    }

    public Vehiculo(Integer idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public Integer getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Integer idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAnofabri() {
        return anofabri;
    }

    public void setAnofabri(Integer anofabri) {
        this.anofabri = anofabri;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    public TipoVehiculo getIdtipovehiculo() {
        return idtipovehiculo;
    }

    public void setIdtipovehiculo(TipoVehiculo idtipovehiculo) {
        this.idtipovehiculo = idtipovehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvehiculo != null ? idvehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idvehiculo == null && other.idvehiculo != null) || (this.idvehiculo != null && !this.idvehiculo.equals(other.idvehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityDB.Vehiculo[ idvehiculo=" + idvehiculo + " ]";
    }
    
}
