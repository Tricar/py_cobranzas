/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "TipoVehiculo.findByNombre", query = "SELECT t FROM TipoVehiculo t WHERE t.nombre = :nombre")})
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
    @Lob
    @Column(name = "fechareg")
    private byte[] fechareg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipovehiculo")
    private List<Vehiculo> vehiculoList;

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

    public byte[] getFechareg() {
        return fechareg;
    }

    public void setFechareg(byte[] fechareg) {
        this.fechareg = fechareg;
    }

    @XmlTransient
    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
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
        return "cobranza.models.TipoVehiculo[ idtipovehiculo=" + idtipovehiculo + " ]";
    }
    
}
