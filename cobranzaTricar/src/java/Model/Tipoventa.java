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
 * Color generated by hbm2java
 */
@Entity
@Table(name = "Tipoventa", schema = "dbo", catalog = "cobranzas"
)
public class Tipoventa implements java.io.Serializable {

    private Integer idtipoventa;
    private String descripcion;
    private Date created;
    private Set ventas = new HashSet(0);

    public Tipoventa() {
    }

    public Tipoventa(Integer idtipoventa) {
        this.idtipoventa = idtipoventa;
    }

    public Tipoventa(Integer idtipoventa, String descripcion, Date created, Set ventas) {
        this.idtipoventa = idtipoventa;
        this.descripcion = descripcion;
        this.created = created;
        this.ventas = ventas;
    }

    @Id

    @Column(name = "idtipoventa", unique = true, nullable = false)
    public Integer getIdtipoventa() {
        return this.idtipoventa;
    }

    public void setIdtipoventa(Integer idtipoventa) {
        this.idtipoventa = idtipoventa;
    }

    @Column(name = "descripcion", nullable = false, length = 50)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Tipoventa")
    public Set getVentas() {
        return this.ventas;
    }

    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idtipoventa != null)
                ? idtipoventa.equals(((Tipoventa) other).idtipoventa)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idtipoventa != null)
                ? (getClass().hashCode() + idtipoventa.hashCode())
                : super.hashCode();
    }

}
