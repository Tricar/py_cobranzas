/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.controllers;

import cobranza.dao.TipoAnexoDAO;
import cobranza.models.TipoAnexo;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sistemas2
 */
@ManagedBean
@SessionScoped
public class TipousuarioController {

    @EJB
    TipoAnexoDAO dao = new TipoAnexoDAO();
    
    public TipousuarioController() {
    }
    
    public String index(){
        return "/tipousuario/index";
    }
    
    public List<TipoAnexo> listado(){
        return dao.findAll();
    }
        
}
