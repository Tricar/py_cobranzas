/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityDB;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tipo_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVehiculo.findAll", query = "SELECT t FROM TipoVehiculo t"),
    @NamedQuery(name = "TipoVehiculo.findByIdtipovehiculo", query = "SELECT t FROM TipoVehiculo t WHERE t.idtipovehiculo = :idtipovehiculo"),
    @NamedQuery(name = "TipoVehiculo.findByNombre", query = "SELECT t FROM TipoVehiculo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoVehiculo.findByFechareg", query = "SELECT t FROM TipoVehiculo t WHERE t.fechareg = :fechareg")})
public class TipoVehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipovehiculo")
    private Integer idtipovehiculo;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipovehiculo")
    private Collection<Vehiculo> vehiculoCollection;

    public TipoVehiculo() {
    }

    public TipoVehiculo(Integer idtipovehiculo) {
        this.idtipovehiculo = idtipovehiculo;
    }

    public Integer getIdtipovehiculo() {
        return idtipovehiculo;
    }

    public void setIdtipovehiculo(Integer idtipovehiculo) {
        this.idtipovehiculo = idtipovehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipovehiculo != null ? idtipovehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVehiculo)) {
            return false;
        }
        TipoVehiculo other = (TipoVehiculo) object;
        if ((this.idtipovehiculo == null && other.idtipovehiculo != null) || (this.idtipovehiculo != null && !this.idtipovehiculo.equals(other.idtipovehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityDB.TipoVehiculo[ idtipovehiculo=" + idtipovehiculo + " ]";
    }
    
}
