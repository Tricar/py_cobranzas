package cobranza.entidad;
// Generated 23/07/2015 09:19:54 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TipoAnexo generated by hbm2java
 */
public class TipoAnexo  implements java.io.Serializable {


     private int idtipoanexo;
     private String nombre;
     private Date fechareg;
     private Set menuTipoanexos = new HashSet(0);
     private Set anexos = new HashSet(0);

    public TipoAnexo() {
    }

	
    public TipoAnexo(int idtipoanexo) {
        this.idtipoanexo = idtipoanexo;
    }
    public TipoAnexo(int idtipoanexo, String nombre, Date fechareg, Set menuTipoanexos, Set anexos) {
       this.idtipoanexo = idtipoanexo;
       this.nombre = nombre;
       this.fechareg = fechareg;
       this.menuTipoanexos = menuTipoanexos;
       this.anexos = anexos;
    }
   
    public int getIdtipoanexo() {
        return this.idtipoanexo;
    }
    
    public void setIdtipoanexo(int idtipoanexo) {
        this.idtipoanexo = idtipoanexo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }
    public Set getMenuTipoanexos() {
        return this.menuTipoanexos;
    }
    
    public void setMenuTipoanexos(Set menuTipoanexos) {
        this.menuTipoanexos = menuTipoanexos;
    }
    public Set getAnexos() {
        return this.anexos;
    }
    
    public void setAnexos(Set anexos) {
        this.anexos = anexos;
    }




}


