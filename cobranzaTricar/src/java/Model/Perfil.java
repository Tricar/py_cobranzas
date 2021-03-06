package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1

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
 * Perfil generated by hbm2java
 */
@Entity
@Table(name = "perfil",
         schema = "dbo",
         catalog = "cobranzas"
)
public class Perfil implements java.io.Serializable {

    private Integer idperfil;
    private String descripcion;
    private String abrev;
    private Date created;
    private Set perfilmenus = new HashSet(0);
    private Set usuarios = new HashSet(0);

    public Perfil() {
    }

    public Perfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public Perfil(Integer idperfil, String descripcion, String abrev, Date created, Set perfilmenus, Set usuarios) {
        this.idperfil = idperfil;
        this.descripcion = descripcion;
        this.abrev = abrev;
        this.created = created;
        this.perfilmenus = perfilmenus;
        this.usuarios = usuarios;
    }

    @Id
    @Column(name = "idperfil", unique = true, nullable = false)
    public Integer getIdperfil() {
        return this.idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    @Column(name = "descripcion", length = 20)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "abrev", length = 2)
    public String getAbrev() {
        return this.abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
    public Set getPerfilmenus() {
        return this.perfilmenus;
    }

    public void setPerfilmenus(Set perfilmenus) {
        this.perfilmenus = perfilmenus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
    public Set getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        return (idperfil != null)
                ? (getClass().hashCode() + idperfil.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idperfil != null)
                ? idperfil.equals(((Perfil) other).idperfil)
                : (other == this);
    }

}
