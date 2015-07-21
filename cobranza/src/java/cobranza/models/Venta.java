/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sistemas2
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByIdanexo", query = "SELECT v FROM Venta v WHERE v.ventaPK.idanexo = :idanexo"),
    @NamedQuery(name = "Venta.findByIdvehiculo", query = "SELECT v FROM Venta v WHERE v.ventaPK.idvehiculo = :idvehiculo"),
    @NamedQuery(name = "Venta.findByIdempresa", query = "SELECT v FROM Venta v WHERE v.ventaPK.idempresa = :idempresa"),
    @NamedQuery(name = "Venta.findByIdventa", query = "SELECT v FROM Venta v WHERE v.ventaPK.idventa = :idventa"),
    @NamedQuery(name = "Venta.findByIdpago", query = "SELECT v FROM Venta v WHERE v.ventaPK.idpago = :idpago")})
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaPK ventaPK;
    @Lob
    @Column(name = "fechareg")
    private byte[] fechareg;
    @JoinColumn(name = "idanexo", referencedColumnName = "idanexo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Anexo anexo;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;
    @JoinColumn(name = "idpago", referencedColumnName = "idpago", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pago pago;
    @JoinColumn(name = "idvehiculo", referencedColumnName = "idvehiculo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vehiculo vehiculo;

    public Venta() {
    }

    public Venta(VentaPK ventaPK) {
        this.ventaPK = ventaPK;
    }

    public Venta(int idanexo, int idvehiculo, int idempresa, int idventa, int idpago) {
        this.ventaPK = new VentaPK(idanexo, idvehiculo, idempresa, idventa, idpago);
    }

    public VentaPK getVentaPK() {
        return ventaPK;
    }

    public void setVentaPK(VentaPK ventaPK) {
        this.ventaPK = ventaPK;
    }

    public byte[] getFechareg() {
        return fechareg;
    }

    public void setFechareg(byte[] fechareg) {
        this.fechareg = fechareg;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaPK != null ? ventaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.ventaPK == null && other.ventaPK != null) || (this.ventaPK != null && !this.ventaPK.equals(other.ventaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cobranza.models.Venta[ ventaPK=" + ventaPK + " ]";
    }
    
}
