package CRUD.Entidad;
// Generated 23/07/2015 03:57:55 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Rolmenu generated by hbm2java
 */
@Entity
@Table(name="rolmenu"
    ,catalog="cobranzas"
)
public class Rolmenu  implements java.io.Serializable {


     private Integer idRolmenu;
     private int rolIdRol;
     private int menuIdMenu;

    public Rolmenu() {
    }

    public Rolmenu(int rolIdRol, int menuIdMenu) {
       this.rolIdRol = rolIdRol;
       this.menuIdMenu = menuIdMenu;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idRolmenu", unique=true, nullable=false)
    public Integer getIdRolmenu() {
        return this.idRolmenu;
    }
    
    public void setIdRolmenu(Integer idRolmenu) {
        this.idRolmenu = idRolmenu;
    }

    
    @Column(name="Rol_idRol", nullable=false)
    public int getRolIdRol() {
        return this.rolIdRol;
    }
    
    public void setRolIdRol(int rolIdRol) {
        this.rolIdRol = rolIdRol;
    }

    
    @Column(name="Menu_idMenu", nullable=false)
    public int getMenuIdMenu() {
        return this.menuIdMenu;
    }
    
    public void setMenuIdMenu(int menuIdMenu) {
        this.menuIdMenu = menuIdMenu;
    }




}


