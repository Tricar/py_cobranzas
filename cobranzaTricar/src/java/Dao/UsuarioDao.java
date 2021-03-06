package Dao;

import Model.Usuario;
import java.util.List;
import org.hibernate.Session;

public interface UsuarioDao {
    
    public boolean registrar(Session session, Usuario usuario)throws Exception;
    public List<Usuario> verTodo(Session session) throws Exception;
    public Usuario verByIdusuario(Session session, Integer idusuario) throws Exception;
    public Usuario verByUsuario(Session session, String usuario) throws Exception;
    public Usuario verByUsuarioDifer(Session session,Integer idusuario, String usuario) throws Exception;
    public boolean modificar(Session session, Usuario usuario) throws Exception;
    public boolean eliminar (Session session, Usuario usuario) throws Exception;
    
}
