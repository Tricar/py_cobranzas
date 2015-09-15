package Model;
// Generated 03/09/2015 12:09:40 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Perfil generated by hbm2java
 */
@Entity
@Table(name = "perfil", schema = "dbo", catalog = "cobranzas"
)
public class Perfil implements java.io.Serializable {

    private Integer idperfil;
    private String descripcion;
    private Boolean sistema;
    private Boolean sisUsu;
    private Boolean sisEmp;
    private Boolean sisPer;
    private Boolean mante;
    private Boolean manCli;
    private Boolean manArt;
    private Boolean manMod;
    private Boolean manCol;
    private Boolean venta;
    private Boolean venLis;
    private Boolean venReg;
    private Boolean venCon;
    private Set usuarios = new HashSet(0);

    public Perfil() {
    }

    public Perfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public Perfil(Integer idperfil, String descripcion, Boolean sistema, Boolean sisEmp, Boolean sisUsu, Boolean sisPer, Boolean mante, Boolean manCli, Boolean manArt, Boolean manMod, Boolean manCol, Boolean venta, Boolean venLis, Boolean venReg, Boolean venCon, Set usuarios) {
        this.idperfil = idperfil;
        this.descripcion = descripcion;
        this.sistema = sistema;
        this.sisEmp = sisEmp;
        this.sisUsu = sisUsu;
        this.sisPer = sisPer;
        this.mante = mante;
        this.manCli = manCli;
        this.manArt = manArt;
        this.manMod = manMod;
        this.manCol = manCol;
        this.venta = venta;
        this.venLis = venLis;
        this.venReg = venReg;
        this.venCon = venCon;
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

    @Column(name = "sistema")
    public Boolean getSistema() {
        return this.sistema;
    }

    public void setSistema(Boolean sistema) {
        this.sistema = sistema;
    }

    @Column(name = "sisUsu")
    public Boolean getSisUsu() {
        return this.sisUsu;
    }

    public void setSisUsu(Boolean sisUsu) {
        this.sisUsu = sisUsu;
    }
    
    @Column(name = "sisEmp")
    public Boolean getSisEmp() {
        return this.sisEmp;
    }

    public void setSisEmp(Boolean sisEmp) {
        this.sisEmp = sisEmp;
    }

    @Column(name = "sisPer")
    public Boolean getSisPer() {
        return this.sisPer;
    }

    public void setSisPer(Boolean sisPer) {
        this.sisPer = sisPer;
    }

    @Column(name = "mante")
    public Boolean getMante() {
        return this.mante;
    }

    public void setMante(Boolean mante) {
        this.mante = mante;
    }

    @Column(name = "manCli")
    public Boolean getManCli() {
        return this.manCli;
    }

    public void setManCli(Boolean manCli) {
        this.manCli = manCli;
    }

    @Column(name = "manArt")
    public Boolean getManArt() {
        return this.manArt;
    }

    public void setManArt(Boolean manArt) {
        this.manArt = manArt;
    }

    @Column(name = "manMod")
    public Boolean getManMod() {
        return this.manMod;
    }

    public void setManMod(Boolean manMod) {
        this.manMod = manMod;
    }

    @Column(name = "manCol")
    public Boolean getManCol() {
        return this.manCol;
    }

    public void setManCol(Boolean manCol) {
        this.manCol = manCol;
    }

    @Column(name = "venta")
    public Boolean getVenta() {
        return this.venta;
    }

    public void setVenta(Boolean venta) {
        this.venta = venta;
    }

    @Column(name = "venLis")
    public Boolean getVenLis() {
        return this.venLis;
    }

    public void setVenLis(Boolean venLis) {
        this.venLis = venLis;
    }

    @Column(name = "venReg")
    public Boolean getVenReg() {
        return this.venReg;
    }

    public void setVenReg(Boolean venReg) {
        this.venReg = venReg;
    }
    
    @Column(name = "venCon")
    public Boolean getVenCon() {
        return this.venCon;
    }

    public void setVenCon(Boolean venCon) {
        this.venCon = venCon;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
    public Set getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idperfil != null)
                ? idperfil.equals(((Perfil) other).idperfil)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idperfil != null)
                ? (getClass().hashCode() + idperfil.hashCode())
                : super.hashCode();
    }
}
