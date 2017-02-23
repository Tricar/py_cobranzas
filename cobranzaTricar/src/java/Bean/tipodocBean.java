package Bean;

import Dao.TipodocDao;
import Dao.TipodocDaoImp;
import Model.Tipodoc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class tipodocBean implements Serializable{
    private List<Tipodoc> listafiltrada = new ArrayList();
    private Tipodoc tipodoc = new Tipodoc();
    
    public tipodocBean() {
    }
    
    public List listaTipoDoc(String tipo){
        TipodocDao tipodao = new TipodocDaoImp();        
        listafiltrada = tipodao.mostrarxTipo(tipo);
        return listafiltrada;
    }
    
    public List listaTipoDocs(String tipo, String tipos){
        TipodocDao tipodao = new TipodocDaoImp();        
        listafiltrada = tipodao.mostrarxTipos(tipo, tipos);
        return listafiltrada;
    }
    
    public Tipodoc objTipo(String tipo, String tienda, String empresa){
        TipodocDao tipodao = new TipodocDaoImp();
        tipodoc = tipodao.tipodoc(tipo, tienda, empresa);
        return tipodoc;
    }
    
    public void modificar (Tipodoc tipo, int corre){
        tipodoc = tipo;
        TipodocDao tipodao = new TipodocDaoImp();
        tipodoc.setCorrelativo(corre);
        tipodao.modificarTipodoc(tipodoc);
    }

    public List<Tipodoc> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Tipodoc> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public Tipodoc getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(Tipodoc tipodoc) {
        this.tipodoc = tipodoc;
    }
}
