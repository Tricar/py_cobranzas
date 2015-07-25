package CRUD.Entidad;
// Generated 25/07/2015 09:13:04 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,catalog="cobranzas"
)
public class Usuario  implements java.io.Serializable {


     private Integer id;
     private Rol rol;
     private String usuario;
     private String clave;
     private String email;
     private Boolean estado;
     private String usuariocreacion;
     private Date fechacreacion;
     private String usuariomodificacion;
     private Date fechamodificacion;

    public Usuario() {
    }

	
    public Usuario(Rol rol, String usuario, String clave, String email, String usuariocreacion, Date fechacreacion) {
        this.rol = rol;
        this.usuario = usuario;
        this.clave = clave;
        this.email = email;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
    }
    public Usuario(Rol rol, String usuario, String clave, String email, Boolean estado, String usuariocreacion, Date fechacreacion, String usuariomodificacion, Date fechamodificacion) {
       this.rol = rol;
       this.usuario = usuario;
       this.clave = clave;
       this.email = email;
       this.estado = estado;
       this.usuariocreacion = usuariocreacion;
       this.fechacreacion = fechacreacion;
       this.usuariomodificacion = usuariomodificacion;
       this.fechamodificacion = fechamodificacion;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rol_id", nullable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name="usuario", nullable=false, length=30)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="clave", nullable=false, length=32)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }

    
    @Column(name="email", nullable=false, length=60)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="estado")
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
    @Column(name="usuariocreacion", nullable=false, length=25)
    public String getUsuariocreacion() {
        return this.usuariocreacion;
    }
    
    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechacreacion", nullable=false, length=19)
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    
    @Column(name="usuariomodificacion", length=25)
    public String getUsuariomodificacion() {
        return this.usuariomodificacion;
    }
    
    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechamodificacion", length=19)
    public Date getFechamodificacion() {
        return this.fechamodificacion;
    }
    
    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }




}


