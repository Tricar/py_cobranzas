package Dao;

import Model.Usuario;
import java.util.List;

public interface UsuarioDao {

    public Usuario buscarPorUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
    public List<Usuario> mostrarUsuario();
    public void insertarUsuario(Usuario usuario);
    public void modificarUsuario (Usuario usuario);
    public void eliminarUsuario (Usuario usuario);
}
