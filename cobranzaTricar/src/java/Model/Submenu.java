package Model;
// Generated 21/04/2016 10:03:10 AM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "submenu", schema = "dbo", catalog = "cobranzas"
)
public class Submenu implements java.io.Serializable {

    private Integer idsubmenu;
    private String submenu;
    private String url;
    private String icon;
    private Date created;
    private Integer idmenuanterior;
    private Menu menu;

    public Submenu() {
    }

    public Submenu(Integer idsubmenu) {
        this.idsubmenu = idsubmenu;
    }

    public Submenu(Integer idsubmenu, String submenu, String url, String icon, Date created, Integer idmenuanterior, Menu menu) {
        this.idsubmenu = idsubmenu;
        this.submenu = submenu;
        this.url = url;
        this.icon = icon;
        this.created = created;
        this.idmenuanterior = idmenuanterior;
        this.menu = menu;
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

    @Column(name = "idmenuanterior")
    public Integer getIdmenuanterior() {
        return this.idmenuanterior;
    }

    public void setIdmenuanterior(Integer idmenuanterior) {
        this.idmenuanterior = idmenuanterior;
    }

    @Column(name = "submenu", nullable = false, length = 50)
    public String getSubmenu() {
        return this.submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    @Column(name = "url", nullable = false, length = 50)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "icon", nullable = false, length = 50)
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idsubmenu != null)
                ? idsubmenu.equals(((Submenu) other).idsubmenu)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idsubmenu != null)
                ? (getClass().hashCode() + idsubmenu.hashCode())
                : super.hashCode();
    }

}
