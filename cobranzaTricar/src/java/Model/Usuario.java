package Model;
// Generated 11/09/2015 02:53:24 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Usuario  implements java.io.Serializable {


     private Integer idusuario;
     private Anexo anexo;
     private Perfil perfil;
     private String usuario;
     private String clave;
     private Date fechareg;

    public Usuario() {
    }

	
    public Usuario(Integer idusuario, String usuario, String clave) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.clave = clave;
    }
    public Usuario(Integer idusuario, Anexo anexo, Perfil perfil, String usuario, String clave, Date fechareg) {
       this.idusuario = idusuario;
       this.anexo = anexo;
       this.perfil = perfil;
       this.usuario = usuario;
       this.clave = clave;
       this.fechareg = fechareg;
    }
   
     @Id 

    
    @Column(name="idusuario", unique=true, nullable=false)
    public Integer getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idanexo")
    public Anexo getAnexo() {
        return this.anexo;
    }
    
    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idperfil")
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    
    @Column(name="usuario", nullable=false, length=10)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="clave", nullable=false, length=150)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechareg", length=23)
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idusuario != null)
                ? idusuario.equals(((Usuario) other).idusuario)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idusuario != null)
                ? (getClass().hashCode() + idusuario.hashCode())
                : super.hashCode();
    }
}


