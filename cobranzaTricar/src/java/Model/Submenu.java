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
 * Submenu generated by hbm2java
 */
@Entity
@Table(name = "submenu",
        schema = "dbo",
        catalog = "cobranzas"
)
public class Submenu implements java.io.Serializable {

    private Integer idsubmenu;
    private Menu menu;
    private String submenu;
    private String url;
    private String icon;
    private Integer idmenuanterior;
    private Date created;
    private Set perfilsubmenus = new HashSet(0);

    public Submenu() {
    }

    public Submenu(Integer idsubmenu) {
        this.idsubmenu = idsubmenu;
    }

    public Submenu(Integer idsubmenu, Menu menu, String submenu, String url, String icon, Integer idmenuanterior, Date created, Set perfilsubmenus) {
        this.idsubmenu = idsubmenu;
        this.menu = menu;
        this.submenu = submenu;
        this.url = url;
        this.icon = icon;
        this.idmenuanterior = idmenuanterior;
        this.created = created;
        this.perfilsubmenus = perfilsubmenus;
    }

    @Id

    @Column(name = "idsubmenu", unique = true, nullable = false)
    public Integer getIdsubmenu() {
        return this.idsubmenu;
    }

    public void setIdsubmenu(Integer idsubmenu) {
        this.idsubmenu = idsubmenu;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmenu")
    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Column(name = "submenu", length = 50)
    public String getSubmenu() {
        return this.submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    @Column(name = "url", length = 50)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "icon", length = 50)
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "idmenuanterior")
    public Integer getIdmenuanterior() {
        return this.idmenuanterior;
    }

    public void setIdmenuanterior(Integer idmenuanterior) {
        this.idmenuanterior = idmenuanterior;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "submenu")
    public Set getPerfilsubmenus() {
        return this.perfilsubmenus;
    }

    public void setPerfilsubmenus(Set perfilsubmenus) {
        this.perfilsubmenus = perfilsubmenus;
    }

    @Override
    public int hashCode() {
        return (idsubmenu != null)
                ? (getClass().hashCode() + idsubmenu.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idsubmenu != null)
                ? idsubmenu.equals(((Submenu) other).idsubmenu)
                : (other == this);
    }

}
