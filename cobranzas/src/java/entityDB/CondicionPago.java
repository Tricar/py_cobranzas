/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityDB;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "condicion_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CondicionPago.findAll", query = "SELECT c FROM CondicionPago c"),
    @NamedQuery(name = "CondicionPago.findByIdcondicionpago", query = "SELECT c FROM CondicionPago c WHERE c.idcondicionpago = :idcondicionpago"),
    @NamedQuery(name = "CondicionPago.findByNombre", query = "SELECT c FROM CondicionPago c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CondicionPago.findByFechareg", query = "SELECT c FROM CondicionPago c WHERE c.fechareg = :fechareg")})
public class CondicionPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcondicionpago")
    private Integer idcondicionpago;
    @Size(max = 18)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 18)
    @Column(name = "fechareg")
    private String fechareg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcondicionpago")
    private Collection<Pago> pagoCollection;

    public CondicionPago() {
    }

    public CondicionPago(Integer idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }

    public Integer getIdcondicionpago() {
        return idcondicionpago;
    }

    public void setIdcondicionpago(Integer idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechareg() {
        return fechareg;
    }

    public void setFechareg(String fechareg) {
        this.fechareg = fechareg;
    }

    @XmlTransient
    public Collection<Pago> getPagoCollection() {
        return pagoCollection;
    }

    public void setPagoCollection(Collection<Pago> pagoCollection) {
        this.pagoCollection = pagoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcondicionpago != null ? idcondicionpago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CondicionPago)) {
            return false;
        }
        CondicionPago other = (CondicionPago) object;
        if ((this.idcondicionpago == null && other.idcondicionpago != null) || (this.idcondicionpago != null && !this.idcondicionpago.equals(other.idcondicionpago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityDB.CondicionPago[ idcondicionpago=" + idcondicionpago + " ]";
    }
    
}
