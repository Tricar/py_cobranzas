package Dao;
import Model.Anexo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Dajoh
 */
public interface AnexoDao {
    
    public boolean registrar(Session session, Anexo anexo)throws Exception;
    public List<Anexo> verTodo(Session session) throws Exception;
    public Anexo verByIdanexo(Session session, Integer idanexo) throws Exception;
    public Anexo verByAnexo(Session session, String nombre) throws Exception;
    public Anexo verByAnexoDifer(Session session,Integer idanexo, String nombre) throws Exception;
    public boolean modificar(Session session, Anexo anexo) throws Exception;
    public boolean eliminar (Session session, Anexo anexo) throws Exception;   
    public List<Anexo> filtarTipo(String tipo);
    public List<Anexo> filtarTipoDos(String tipo, String tipo1, String tipo2);
    public List<Anexo> buscarxNombre(String nombre);
    public List<Anexo> buscarCliente(String nombre, String tipo);
    public Anexo cargarxCredito(Integer idanexo);
}
