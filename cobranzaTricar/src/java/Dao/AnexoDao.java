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
    public List<Anexo> verCliente(Session session) throws Exception;
    public List<Anexo> verEmpleado(Session session) throws Exception;
    public List<Anexo> verVendedor(Session session) throws Exception;
    public Anexo verByIdanexo(Session session, Integer idanexo) throws Exception;
    public Anexo verByDocumento(Session session, String dni) throws Exception;
    public Anexo verByDocumentoDifer(Session session,Integer idanexo, String dni) throws Exception;
    public boolean modificar(Session session, Anexo anexo) throws Exception;
    public boolean eliminar (Session session, Anexo anexo) throws Exception;   
    public List<Anexo> filtarTipo(String tipo);
    public List<Anexo> filtarTipoTres(String tipo, String tipo1, String tipo2);
    public List<Anexo> filtarTipoDos(String tipo, String tipo1);
    public List<Anexo> filtarAval(String tipo);
    public List<Anexo> buscarxNombre(String nombre);
    public List<Anexo> buscarClienteNombre(String nombre, String tipo, String tipo1);
    public List<Anexo> buscarClientexDoc(String dni, String tipo, String tipo1);
    public Anexo cargarxCredito(Integer idanexo);
    public Anexo cargarClientexDoc(String dni, String tipo, String tipo1);
    public List<Anexo> buscarClienteDni(String dni, String tipo, String tipo1);
}
