package Dao;

import Model.Vehiculoanexo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface VehiculoanexoDao {    
    public boolean registrar(Session session, Vehiculoanexo vehiculoanexo)throws Exception;
    public List<Vehiculoanexo> verTodo(Session session) throws Exception; 
    public Vehiculoanexo verByCodigo(Session session, Integer idvehiculoanexo) throws Exception;    
    public List<Vehiculoanexo> verByFamilia(Session session, Integer idanexo) throws Exception;
    public Vehiculoanexo verBySubfamilia(Session session, String vehiculoanexo) throws Exception;
    public Vehiculoanexo verBySubfamiliaD(Session session,Integer idvehiculoanexo, String reporte) throws Exception;
    public boolean modificar(Session session, Vehiculoanexo vehiculoanexo) throws Exception;
    public boolean eliminar (Session session, Vehiculoanexo vehiculoanexo) throws Exception;
}
