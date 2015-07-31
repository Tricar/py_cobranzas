package Bean;

import Dao.EmpresaDao;
import Dao.EmpresaDaoImplements;
import Model.Empresa;
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
public class empresaBean {

    public Empresa empresa = new Empresa();
    public List<Empresa> empresas;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {
        EmpresaDao tipodao = new EmpresaDaoImplements();
        empresas = tipodao.mostrarEmpresa();
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
    
    public empresaBean() {
    }
    
    public void insertar (){
        EmpresaDao linkDao = new EmpresaDaoImplements();
        Date d = new Date();
        empresa.setFechareg(d);
        linkDao.insertarEmpresa(empresa);
        empresa = new Empresa();
        
    }
    
    public void modificar(){
        EmpresaDao linkDao = new EmpresaDaoImplements();
        Date d = new Date();
        empresa.setFechareg(d);
        linkDao.modificarEmpresa(empresa);
        empresa = new Empresa();
    }
    
    public void eliminar(){
        EmpresaDao linkDao = new EmpresaDaoImplements();
        linkDao.eliminarEmpresa(empresa);
        empresa = new Empresa();
    }
    
}
