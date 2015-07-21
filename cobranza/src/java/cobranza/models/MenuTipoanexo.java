/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sistemas2
 */
@Entity
@Table(name = "menu_tipoanexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuTipoanexo.findAll", query = "SELECT m FROM MenuTipoanexo m"),
    @NamedQuery(name = "MenuTipoanexo.findByIdmenutipo", query = "SELECT m FROM MenuTipoanexo m WHERE m.idmenutipo = :idmenutipo")})
public class MenuTipoanexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmenutipo")
    private Integer idmenutipo;
    @Lob
    @Column(name = "fechareg")
    private byte[] fechareg;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(optional = false)
    private Menu idmenu;
    @JoinColumn(name = "idtipoanexo", referencedColumnName = "idtipoanexo")
    @ManyToOne(optional = false)
    private TipoAnexo idtipoanexo;

    public MenuTipoanexo() {
    }

    public MenuTipoanexo(Integer idmenutipo) {
        this.idmenutipo = idmenutipo;
    }

    public Integer getIdmenutipo() {
        return idmenutipo;
    }

    public void setIdmenutipo(Integer idmenutipo) {
        this.idmenutipo = idmenutipo;
    }

    public byte[] getFechareg() {
        return fechareg;
    }

    public void setFechareg(byte[] fechareg) {
        this.fechareg = fechareg;
    }

    public Menu getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Menu idmenu) {
        this.idmenu = idmenu;
    }

    public TipoAnexo getIdtipoanexo() {
        return idtipoanexo;
    }

    public void setIdtipoanexo(TipoAnexo idtipoanexo) {
        this.idtipoanexo = idtipoanexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenutipo != null ? idmenutipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuTipoanexo)) {
            return false;
        }
        MenuTipoanexo other = (MenuTipoanexo) object;
        if ((this.idmenutipo == null && other.idmenutipo != null) || (this.idmenutipo != null && !this.idmenutipo.equals(other.idmenutipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cobranza.models.MenuTipoanexo[ idmenutipo=" + idmenutipo + " ]";
    }
    
}
