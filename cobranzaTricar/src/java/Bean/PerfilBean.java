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
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@SessionScoped
public class PerfilBean implements Serializable{
    private Perfil perfil = new Perfil();
    private List<Perfil> perfiles;
    private List<SelectItem> SelectItemsPerfil;
    
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
    
    public List<SelectItem> getSelectItemsPerfil() {
        this.SelectItemsPerfil = new ArrayList<SelectItem>();
        PerfilDao perfilDao = new PerfilDaoImpl();
        List<Perfil> tipos = perfilDao.mostrarPerfil();
        for (Perfil tipo : tipos) {
            SelectItem selecItem = new SelectItem(tipo, tipo.getDescripcion());
            this.SelectItemsPerfil.add(selecItem);
        }
        return SelectItemsPerfil;
    }
    
    public void insertarPerfil() {
        PerfilDao linkDao = new PerfilDaoImpl();
        linkDao.crearperfil(perfil);
        perfil = new Perfil();
    }
    
    public void eliminarPerfil() {
        PerfilDao linkDao = new PerfilDaoImpl();
        linkDao.eliminarperfil(perfil);
        perfil = new Perfil();
    }
    
}
