/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.PerfilDao;
import Dao.PerfilDaoImpl;
import Model.Perfil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@SessionScoped
public class PerfilBean implements Serializable{
    private Perfil perfil;
    private List<Perfil> perfiles;
    
    public PerfilBean() {
    }
    
    public void prepararPerfil(Integer id) {
        PerfilDao perfilDao = new PerfilDaoImpl();
        perfil = perfilDao.buscarPorId(id);
    }

    public void actualizarPerfil() {
        // Condiciones de Menu Principal
        if (perfil.getSisEmp()== true || perfil.getSisPer()== true || perfil.getSisTie()==true || 
                perfil.getSisTipAne()==true || perfil.getSisUsu()==true ) {
            perfil.setSistema(true);
        } else if (perfil.getSisEmp() == false && perfil.getSisPer() == false && perfil.getSisTie()==true && perfil.getSisTipAne()==true && perfil.getSisUsu()==true) {
            perfil.setSistema(false);
        }

        if (perfil.getManArt()== true || perfil.getManCli()== true || perfil.getManCol()== true || 
                perfil.getManConPag()== true || perfil.getManMod()== true || perfil.getManPag()== true || perfil.getManTipArt()== true) {
            perfil.setMante(true);
        } else if (perfil.getManArt() == false && perfil.getManCli() == false && perfil.getManCol()== true && 
                perfil.getManConPag()== true && perfil.getManMod()== true && perfil.getManPag()== true && perfil.getManTipArt()== true) {
            perfil.setMante(false);
        }

        if (perfil.getVenLis()== true || perfil.getVenReg()== true) {
            perfil.setVenta(true);
        } else if (perfil.getVenLis() == false && perfil.getVenReg() == false) {
            perfil.setVenta(false);
        }

        PerfilDao perfilDao = new PerfilDaoImpl();
        perfilDao.actualizar(perfil);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Datos actualizado correctamente", "Hello"));
        perfil = new Perfil();
    }

    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfiles() {
        PerfilDao perfilDao = new PerfilDaoImpl();
        perfiles = perfilDao.buscarTodos();
        return perfiles;
    }
    
}
