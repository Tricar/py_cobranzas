package Bean;

import Dao.UsuarioDao;
import Dao.UsuarioDaoImpl;
import Model.Perfil;
import Model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class usuarioBean implements Serializable{

    private Usuario user = new Usuario();
    private Perfil per = new Perfil();
    private List<Usuario> usuarios;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
    }

    public Perfil getPer() {
        return per;
    }

    public void setPer(Perfil per) {
        this.per = per;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Usuario> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.mostrarUsuario();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void insertar() {
        UsuarioDao linkDao = new UsuarioDaoImpl();
        Date d = new Date();
        user.setFechareg(d);
        linkDao.insertarUsuario(user);
        user = new Usuario();
    }

    public void modificar() {
        UsuarioDao linkDao = new UsuarioDaoImpl();
        linkDao.modificarUsuario(user);
        user = new Usuario();
    }

    public void eliminar() {
        UsuarioDao linkDao = new UsuarioDaoImpl();
        linkDao.eliminarUsuario(user);
        user = new Usuario();
    }
}
