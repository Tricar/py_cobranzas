package Dao;

import Model.Empresa;
import java.util.List;

/**
 *
 * @author master
 */
public interface EmpresaDao {
    public List<Empresa> mostrarEmpresa();
    public void insertarEmpresa(Empresa empresa);
    public void modificarEmpresa (Empresa empresa);
    public void eliminarEmpresa (Empresa empresa);
}
