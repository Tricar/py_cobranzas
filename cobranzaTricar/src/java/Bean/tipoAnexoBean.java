/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.TipoAnexoDao;
import Dao.TipoAnexoDaoImplements;
import Model.Tipoanexo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dajoh
 */
@ManagedBean
@ViewScoped
public class tipoAnexoBean implements Serializable{
    
    public Tipoanexo tipoanexo = new Tipoanexo();
    public List<Tipoanexo> tipoanexos;

    public Tipoanexo getTipoanexo() {
        return tipoanexo;
    }

    public void setTipoanexo(Tipoanexo tipoanexo) {
        this.tipoanexo = tipoanexo;
    }

    public List<Tipoanexo> getTipoanexos() {
        TipoAnexoDao tipodao = new TipoAnexoDaoImplements();
        tipoanexos = tipodao.mostrarTipoAnexo();
        return tipoanexos;
    }

    public void setTipoanexos(List<Tipoanexo> tipoanexos) {
        this.tipoanexos = tipoanexos;
    }
    /**
     * Creates a new instance of tipoAnexoBean
     */
    public tipoAnexoBean() {
    }
    
    public void insertar (){
        TipoAnexoDao linkDao = new TipoAnexoDaoImplements();
        Date d = new Date();
        tipoanexo.setFechareg(d);
        linkDao.insertarTipoAnexo(tipoanexo);
        tipoanexo = new Tipoanexo();
        
    }
    
    public void modificar(){
        TipoAnexoDao linkDao = new TipoAnexoDaoImplements();
        linkDao.modificarTipoAnexo(tipoanexo);
        tipoanexo = new Tipoanexo();
    }
    
    public void eliminar(){
        TipoAnexoDao linkDao = new TipoAnexoDaoImplements();
        linkDao.eliminarTipoAnexo(tipoanexo);
        tipoanexo = new Tipoanexo();
    }
    
 }
