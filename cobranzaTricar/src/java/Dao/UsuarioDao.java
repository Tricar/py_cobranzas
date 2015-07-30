package Dao;

import Model.Usuario;
import java.util.List;

public interface UsuarioDao {

    public Usuario buscarPorUsuario(Usuario usuario);
     public List<Usuario> buscarTodos();
     
     public Usuario registrar(Usuario usuario);
}
