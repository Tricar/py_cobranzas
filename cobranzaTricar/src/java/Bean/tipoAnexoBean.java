/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.TipoAnexoDao;
import Dao.TipoAnexoDaoImplements;
import Model.TipoAnexo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dajoh
 */
@ManagedBean
@ViewScoped
public class tipoAnexoBean {
    
    public TipoAnexo tipoanexo = new TipoAnexo();
    public List<TipoAnexo> tipoanexos;

    public TipoAnexo getTipoanexo() {
        return tipoanexo;
    }

    public void setTipoanexo(TipoAnexo tipoanexo) {
        this.tipoanexo = tipoanexo;
    }

    public List<TipoAnexo> getTipoanexos() {
        return tipoanexos;
    }

    public void setTipoanexos(List<TipoAnexo> tipoanexos) {
        this.tipoanexos = tipoanexos;
    }
    /**
     * Creates a new instance of tipoAnexoBean
     */
    public tipoAnexoBean() {
    }
    
    public void insertar (){
        TipoAnexoDao linkDao = new TipoAnexoDaoImplements();
        linkDao.insertarTipoAnexo(tipoanexo);
        tipoanexo = new TipoAnexo();
    }
    
    public void modificar(){
        TipoAnexoDao linkDao = new TipoAnexoDaoImplements();
        linkDao.modificarTipoAnexo(tipoanexo);
        tipoanexo = new TipoAnexo();
    }
    
    public void eliminar(){
        TipoAnexoDao linkDao = new TipoAnexoDaoImplements();
        linkDao.eliminarTipoAnexo(tipoanexo);
        tipoanexo = new TipoAnexo();
    }
}
