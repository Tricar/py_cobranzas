package Bean;

import Dao.EmpresaDao;
import Dao.EmpresaDaoImp;
import Model.Empresa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class empresaBean implements Serializable{
    
    public Empresa empresa = new Empresa();
    public List<Empresa> empresas;
    /**
     * Creates a new instance of empresaBean
     */
    public empresaBean() {
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {
        EmpresaDao linkdao = new EmpresaDaoImp();
        empresas = linkdao.mostrarEmpresa();
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
    
    public void insertar (){
        EmpresaDao linkdao = new EmpresaDaoImp();
        Date d = new Date();
        empresa.setFecreg(d);
        linkdao.insertarEmpresa(empresa);
        empresa = new Empresa();        
    }
    
    public void modificar(){
        EmpresaDao linkdao = new EmpresaDaoImp();
        linkdao.modificarEmpresa(empresa);
        empresa = new Empresa();
    }
    
    public void eliminar(){
        EmpresaDao linkdao = new EmpresaDaoImp();
        linkdao.eliminarEmpresa(empresa);
        empresa = new Empresa();
    }
}
