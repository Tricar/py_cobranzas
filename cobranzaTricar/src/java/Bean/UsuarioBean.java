package Bean;

import Dao.UsuarioDao;
import Dao.UsuarioDaoImpl;
import Model.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped

public class UsuarioBean {

    private Usuario usuario;
    private List<Usuario> usuarios;

    public UsuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.buscarTodos();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void btnCreateUsuario(ActionEvent Event) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        Date d = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(d);
        this.usuario.setFechareg(java.sql.Date.valueOf(fecha));
        String msg;
        if (usuarioDao.registrar(this.usuario)) {
            msg = "Se Registro correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al Registrar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnUpdateUsuario(ActionEvent Event) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        if (usuarioDao.modificar(this.usuario)) {
            msg = "Se Modifico correctamente el registro";
        } else {
            msg = "Error al Modificar";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void btnDeleteUsuario(ActionEvent Event) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        if (usuarioDao.eliminar(this.usuario.getIdusuario())) {
            msg = "Se Elimino correctamente el registro";
        } else {
            msg = "Error al Eliminar";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
