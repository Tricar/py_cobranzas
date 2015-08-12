package Dao;

import Model.Usuario;
import java.util.List;

public interface UsuarioDao {

    public Usuario buscarPorUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
    public List<Usuario> buscarTodos();
    public boolean registrar(Usuario usuario);
    public boolean modificar(Usuario usuario);
    public boolean eliminar(String user);
}
