package Model;
// Generated 13/08/2015 11:16:31 AM by Hibernate Tools 4.3.1


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
 * Menu generated by hbm2java
 */
@Entity
@Table(name="menu"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Menu  implements java.io.Serializable {


     private int idmenu;
     private String nombre;
     private Date fechareg;
     private String imagen;
     private String url;
     private String action;
     private Integer raiz;
     private Integer orden;
     private Set menutipoanexos = new HashSet(0);

    public Menu() {
    }

	
    public Menu(int idmenu) {
        this.idmenu = idmenu;
    }
    public Menu(int idmenu, String nombre, Date fechareg, String imagen, String url, String action, Integer raiz, Integer orden, Set menutipoanexos) {
       this.idmenu = idmenu;
       this.nombre = nombre;
       this.fechareg = fechareg;
       this.imagen = imagen;
       this.url = url;
       this.action = action;
       this.raiz = raiz;
       this.orden = orden;
       this.menutipoanexos = menutipoanexos;
    }
   
     @Id 

    
    @Column(name="idmenu", unique=true, nullable=false)
    public int getIdmenu() {
        return this.idmenu;
    }
    
    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    
    @Column(name="nombre", length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechareg", length=23)
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    
    @Column(name="imagen", length=100)
    public String getImagen() {
        return this.imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    @Column(name="url", length=100)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    
    @Column(name="action", length=100)
    public String getAction() {
        return this.action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }

    
    @Column(name="raiz")
    public Integer getRaiz() {
        return this.raiz;
    }
    
    public void setRaiz(Integer raiz) {
        this.raiz = raiz;
    }

    
    @Column(name="orden")
    public Integer getOrden() {
        return this.orden;
    }
    
    public void setOrden(Integer orden) {
        this.orden = orden;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="menu")
    public Set getMenutipoanexos() {
        return this.menutipoanexos;
    }
    
    public void setMenutipoanexos(Set menutipoanexos) {
        this.menutipoanexos = menutipoanexos;
    }




}


