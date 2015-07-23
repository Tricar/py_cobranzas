/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sistemas2
 */
@Entity
@Table(name = "tipo_anexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAnexo.findAll", query = "SELECT t FROM TipoAnexo t"),
    @NamedQuery(name = "TipoAnexo.findByIdtipoanexo", query = "SELECT t FROM TipoAnexo t WHERE t.idtipoanexo = :idtipoanexo"),
    @NamedQuery(name = "TipoAnexo.findByNombre", query = "SELECT t FROM TipoAnexo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoAnexo.findByFechareg", query = "SELECT t FROM TipoAnexo t WHERE t.fechareg = :fechareg")})
public class TipoAnexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoanexo")
    private Integer idtipoanexo;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoanexo")
    private List<MenuTipoanexo> menuTipoanexoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoanexo")
    private List<Anexo> anexoList;

    public TipoAnexo() {
    }

    public TipoAnexo(Integer idtipoanexo) {
        this.idtipoanexo = idtipoanexo;
    }

    public Integer getIdtipoanexo() {
        return idtipoanexo;
    }

    public void setIdtipoanexo(Integer idtipoanexo) {
        this.idtipoanexo = idtipoanexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    @XmlTransient
    public List<MenuTipoanexo> getMenuTipoanexoList() {
        return menuTipoanexoList;
    }

    public void setMenuTipoanexoList(List<MenuTipoanexo> menuTipoanexoList) {
        this.menuTipoanexoList = menuTipoanexoList;
    }

    @XmlTransient
    public List<Anexo> getAnexoList() {
        return anexoList;
    }

    public void setAnexoList(List<Anexo> anexoList) {
        this.anexoList = anexoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoanexo != null ? idtipoanexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAnexo)) {
            return false;
        }
        TipoAnexo other = (TipoAnexo) object;
        if ((this.idtipoanexo == null && other.idtipoanexo != null) || (this.idtipoanexo != null && !this.idtipoanexo.equals(other.idtipoanexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cobranza.models.TipoAnexo[ idtipoanexo=" + idtipoanexo + " ]";
    }
    
}
