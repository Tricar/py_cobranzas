package cobranza.entidad;
// Generated 23/07/2015 09:19:54 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CondicionPago generated by hbm2java
 */
public class CondicionPago  implements java.io.Serializable {


     private int idcondicionpago;
     private String nombre;
     private Date fechareg;
     private Set pagos = new HashSet(0);

    public CondicionPago() {
    }

	
    public CondicionPago(int idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }
    public CondicionPago(int idcondicionpago, String nombre, Date fechareg, Set pagos) {
       this.idcondicionpago = idcondicionpago;
       this.nombre = nombre;
       this.fechareg = fechareg;
       this.pagos = pagos;
    }
   
    public int getIdcondicionpago() {
        return this.idcondicionpago;
    }
    
    public void setIdcondicionpago(int idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
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
    public Set getPagos() {
        return this.pagos;
    }
    
    public void setPagos(Set pagos) {
        this.pagos = pagos;
    }




}


