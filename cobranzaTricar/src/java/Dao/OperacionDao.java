/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Operacion;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface OperacionDao {
    
    public boolean insertar(Session session, Operacion operacion) throws Exception;
    public Operacion getUltimoRegistro(Session session) throws Exception;    
    public Operacion verByCodigos(Integer idoperacion);
    public Operacion verByCodigoVenta(String codigo);
    public boolean modificarOD(Operacion operacion);
    public List<Operacion> cargarxEstado (Integer estado);
    public List<Operacion> filtrarFechas (Date date1, Date date2, Integer tipo);
    public List<Operacion> filtrarFechasTipo (Date date1, Date date2);
    public Operacion verByCodigo(Session session, Integer codigo) throws Exception;
    public boolean eliminar (Session session, Operacion perfil) throws Exception;
    public boolean modificar(Session session, Operacion articulo) throws Exception;
    
}
