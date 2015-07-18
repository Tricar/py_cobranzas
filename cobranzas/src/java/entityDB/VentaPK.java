/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityDB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sistemas2
 */
@Embeddable
public class VentaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idanexo")
    private int idanexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idvehiculo")
    private int idvehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idempresa")
    private int idempresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idventa")
    private int idventa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpago")
    private int idpago;

    public VentaPK() {
    }

    public VentaPK(int idanexo, int idvehiculo, int idempresa, int idventa, int idpago) {
        this.idanexo = idanexo;
        this.idvehiculo = idvehiculo;
        this.idempresa = idempresa;
        this.idventa = idventa;
        this.idpago = idpago;
    }

    public int getIdanexo() {
        return idanexo;
    }

    public void setIdanexo(int idanexo) {
        this.idanexo = idanexo;
    }

    public int getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(int idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idanexo;
        hash += (int) idvehiculo;
        hash += (int) idempresa;
        hash += (int) idventa;
        hash += (int) idpago;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaPK)) {
            return false;
        }
        VentaPK other = (VentaPK) object;
        if (this.idanexo != other.idanexo) {
            return false;
        }
        if (this.idvehiculo != other.idvehiculo) {
            return false;
        }
        if (this.idempresa != other.idempresa) {
            return false;
        }
        if (this.idventa != other.idventa) {
            return false;
        }
        if (this.idpago != other.idpago) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityDB.VentaPK[ idanexo=" + idanexo + ", idvehiculo=" + idvehiculo + ", idempresa=" + idempresa + ", idventa=" + idventa + ", idpago=" + idpago + " ]";
    }
    
}
