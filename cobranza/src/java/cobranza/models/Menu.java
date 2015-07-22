/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sistemas2
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findAllOrderMenu", query = "SELECT m FROM Menu m order by m.raiz, m.orden"),
    @NamedQuery(name = "Menu.findByIdmenu", query = "SELECT m FROM Menu m WHERE m.idmenu = :idmenu"),
    @NamedQuery(name = "Menu.findByNombre", query = "SELECT m FROM Menu m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Menu.findByImagen", query = "SELECT m FROM Menu m WHERE m.imagen = :imagen"),
    @NamedQuery(name = "Menu.findByUrl", query = "SELECT m FROM Menu m WHERE m.url = :url"),
    @NamedQuery(name = "Menu.findByAction", query = "SELECT m FROM Menu m WHERE m.action = :action"),
    @NamedQuery(name = "Menu.findByRaiz", query = "SELECT m FROM Menu m WHERE m.raiz = :raiz"),
    @NamedQuery(name = "Menu.findByOrden", query = "SELECT m FROM Menu m WHERE m.orden = :orden")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmenu")
    private Integer idmenu;
    @Size(min=1, max = 50, message="Debe ingresar nombre")
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "fechareg")
    private byte[] fechareg;
    @Size(max = 100)
    @Column(name = "imagen")
    private String imagen;
    @Size(max = 100)
    @Column(name = "url")
    private String url;
    @Size(max = 100)
    @Column(name = "action")
    private String action;
    @Column(name = "raiz")
    @NotNull(message="Debe ingresar Raiz")
    private Integer raiz;
    @Column(name = "orden")
    @NotNull(message="Debe ingresar Orden")
    private Integer orden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmenu")
    private List<MenuTipoanexo> menuTipoanexoList;

    public Menu() {
    }

    public Menu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFechareg() {
        return fechareg;
    }

    public void setFechareg(byte[] fechareg) {
        this.fechareg = fechareg;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getRaiz() {
        return raiz;
    }

    public void setRaiz(Integer raiz) {
        this.raiz = raiz;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<MenuTipoanexo> getMenuTipoanexoList() {
        return menuTipoanexoList;
    }

    public void setMenuTipoanexoList(List<MenuTipoanexo> menuTipoanexoList) {
        this.menuTipoanexoList = menuTipoanexoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + "(" + idmenu + ")";
    }
    
}
