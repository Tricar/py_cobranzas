package Model;
// Generated 21/04/2016 10:03:10 AM by Hibernate Tools 4.3.1

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

/**
 * Perfil generated by hbm2java
 */
@Entity
@Table(name = "menu", schema = "dbo", catalog = "cobranzas"
)
public class Menu implements java.io.Serializable {

    private Integer idmenu;
    private String menu;
    private Perfil perfil;
    private Set submenus = new HashSet(0);

    public Menu() {
    }

    public Menu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Menu(Integer idmenu, String menu, Perfil perfil, Set submenus) {
        this.idmenu = idmenu;
        this.menu = menu;
        this.perfil = perfil;
        this.submenus = submenus;      
    }

    @Id

    @Column(name = "idmenu", unique = true, nullable = false)
    public Integer getIdmenu() {
        return this.idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    @Column(name = "menu", length = 50)
    public String getMenu() {
        return this.menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idperfil")
    public Perfil getPerfil() {
        return this.perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    public Set getSubmenus() {
        return this.submenus;
    }

    public void setSubmenus(Set submenus) {
        this.submenus = submenus;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idmenu != null)
                ? idmenu.equals(((Menu) other).idmenu)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idmenu != null)
                ? (getClass().hashCode() + idmenu.hashCode())
                : super.hashCode();
    }

}
