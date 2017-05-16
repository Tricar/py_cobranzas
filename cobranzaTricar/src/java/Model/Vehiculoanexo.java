package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1

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
 * Vehiculo generated by hbm2java
 */
@Entity
@Table(name = "vehiculoanexo",
        schema = "dbo",
        catalog = "cobranzas"
)
public class Vehiculoanexo implements java.io.Serializable {

    private Integer idvehiculoanexo;
    private Anexo anexo;
    private Vehiculo vehiculo;
    private Anexo vendedor;
    private Date created;
    private Set soportes = new HashSet(0);

    public Vehiculoanexo() {
    }

    public Vehiculoanexo(Integer idvehiculoanexo, Anexo anexo, Vehiculo vehiculo, Anexo vendedor, Date created, Set soportes) {
        this.idvehiculoanexo = idvehiculoanexo;
        this.anexo = anexo;
        this.vehiculo = vehiculo;
        this.vendedor = vendedor;
        this.created = created;
        this.soportes = soportes;
    }

    @Id
    @Column(name = "idvehiculoanexo", unique = true, nullable = false)
    public Integer getIdvehiculoanexo() {
        return this.idvehiculoanexo;
    }

    public void setIdvehiculoanexo(Integer idvehiculoanexo) {
        this.idvehiculoanexo = idvehiculoanexo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idanexo", nullable = false)
    public Anexo getAnexo() {
        return this.anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idvehiculo", nullable = false)
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Column(name = "idvendedor", length = 1)
    public Anexo getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Anexo vendedor) {
        this.vendedor = vendedor;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tiposoporte")
    public Set getSoportes() {
        return this.soportes;
    }

    public void setSoportes(Set soportes) {
        this.soportes = soportes;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idvehiculoanexo != null)
                ? idvehiculoanexo.equals(((Vehiculoanexo) other).idvehiculoanexo)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idvehiculoanexo != null)
                ? (getClass().hashCode() + idvehiculoanexo.hashCode())
                : super.hashCode();
    }

}
