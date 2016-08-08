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
}
