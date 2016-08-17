package Model;
// Generated 21/04/2016 10:03:10 AM by Hibernate Tools 4.3.1

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
    private String abrev;
    private Boolean sistema;
    private Boolean sisVende;
    private Boolean sisEmp;
    private Boolean sisUsu;
    private Boolean sisPer;
    private Boolean sisDist;
    private Boolean sisCaja;
    private Boolean mante;
    private Boolean manCli;
    private Boolean manIng;
    private Boolean manAval;
    private Boolean manArt;
    private Boolean manMod;
    private Boolean manCol;
    private Boolean creco;
    private Boolean creLis;
    private Boolean creReg;
    private Boolean creCon;
    private Boolean creRef;
    private Boolean despacho;
    private Boolean desDes;
    private Boolean desVen;
    private Boolean manAnt;
    private Set usuarios = new HashSet(0);

    public Perfil() {
    }

    public Perfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public Perfil(Integer idperfil, String descripcion, String abrev, Boolean sistema, Boolean sisVende, Boolean sisEmp, Boolean sisUsu, Boolean sisPer, Boolean sisDist, Boolean sisCaja, Boolean mante, Boolean manCli, Boolean manAval, Boolean manArt, Boolean manMod, Boolean manCol, Boolean creco, Boolean creLis, Boolean creReg, Boolean creCon, Boolean creRef, Boolean manIng, Boolean despacho, Boolean desDes, Boolean desVen, Boolean manAnt, Set usuarios) {
        this.idperfil = idperfil;
        this.descripcion = descripcion;
        this.abrev = abrev;
        this.sistema = sistema;
        this.sisVende = sisVende;
        this.sisEmp = sisEmp;
        this.sisUsu = sisUsu;
        this.sisPer = sisPer;
        this.sisDist = sisDist;
        this.sisCaja = sisCaja;
        this.mante = mante;
        this.manCli = manCli;
        this.manAval = manAval;
        this.manArt = manArt;
        this.manMod = manMod;
        this.manCol = manCol;
        this.creco = creco;
        this.creLis = creLis;
        this.creReg = creReg;
        this.creCon = creCon;
        this.creRef = creRef;
        this.manIng = manIng;
        this.despacho = despacho;
        this.desDes = desDes;
        this.desVen = desVen;
        this.manAnt = manAnt;
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

    @Column(name = "sistema")
    public Boolean getSistema() {
        return this.sistema;
    }

    public void setSistema(Boolean sistema) {
        this.sistema = sistema;
    }

    @Column(name = "sisVende")
    public Boolean getSisVende() {
        return this.sisVende;
    }

    public void setSisVende(Boolean sisVende) {
        this.sisVende = sisVende;
    }

    @Column(name = "sisEmp")
    public Boolean getSisEmp() {
        return this.sisEmp;
    }

    public void setSisEmp(Boolean sisEmp) {
        this.sisEmp = sisEmp;
    }

    @Column(name = "sisUsu")
    public Boolean getSisUsu() {
        return this.sisUsu;
    }

    public void setSisUsu(Boolean sisUsu) {
        this.sisUsu = sisUsu;
    }    

    @Column(name = "sisDist")
    public Boolean getSisDist() {
        return this.sisDist;
    }

    public void setSisDist(Boolean sisDist) {
        this.sisDist = sisDist;
    }

    @Column(name = "sisPer")
    public Boolean getSisPer() {
        return this.sisPer;
    }

    public void setSisPer(Boolean sisPer) {
        this.sisPer = sisPer;
    }
    
    @Column(name = "sisCaja")
    public Boolean getSisCaja() {
        return this.sisCaja;
    }

    public void setSisCaja(Boolean sisCaja) {
        this.sisCaja = sisCaja;
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
    
    @Column(name = "manIng")
    public Boolean getManIng() {
        return this.manIng;
    }

    public void setManIng(Boolean manIng) {
        this.manIng = manIng;
    }

    @Column(name = "manAval")
    public Boolean getManAval() {
        return this.manAval;
    }

    public void setManAval(Boolean manAval) {
        this.manAval = manAval;
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

    @Column(name = "creco")
    public Boolean getCreco() {
        return this.creco;
    }

    public void setCreco(Boolean creco) {
        this.creco = creco;
    }

    @Column(name = "creLis")
    public Boolean getCreLis() {
        return this.creLis;
    }

    public void setCreLis(Boolean creLis) {
        this.creLis = creLis;
    }

    @Column(name = "creReg")
    public Boolean getCreReg() {
        return this.creReg;
    }

    public void setCreReg(Boolean creReg) {
        this.creReg = creReg;
    }

    @Column(name = "creCon")
    public Boolean getCreCon() {
        return this.creCon;
    }

    public void setCreCon(Boolean creCon) {
        this.creCon = creCon;
    }
    
    @Column(name = "creRef")
    public Boolean getCreRef() {
        return this.creRef;
    }

    public void setCreRef(Boolean creRef) {
        this.creRef = creRef;
    }
    
     @Column(name = "despacho")
    public Boolean getDespacho() {
        return this.despacho;
    }

    public void setDespacho(Boolean despacho) {
        this.despacho = despacho;
    }
    
    @Column(name = "desDes")
    public Boolean getDesDes() {
        return this.desDes;
    }

    public void setDesDes(Boolean desDes) {
        this.desDes = desDes;
    }
    
    @Column(name = "desVen")
    public Boolean getDesVen() {
        return this.desVen;
    }

    public void setDesVen(Boolean desVen) {
        this.desVen = desVen;
    }
    
    @Column(name = "manAnt")
    public Boolean getManAnt() {
        return this.manAnt;
    }

    public void setManAnt(Boolean manAnt) {
        this.manAnt = manAnt;
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
