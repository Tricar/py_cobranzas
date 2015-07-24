package Model;
// Generated 24/07/2015 09:15:20 AM by Hibernate Tools 4.3.1


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
     private Set<Pago> pagos = new HashSet<Pago>(0);

    public CondicionPago() {
    }

	
    public CondicionPago(int idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }
    public CondicionPago(int idcondicionpago, String nombre, Date fechareg, Set<Pago> pagos) {
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
    public Set<Pago> getPagos() {
        return this.pagos;
    }
    
    public void setPagos(Set<Pago> pagos) {
        this.pagos = pagos;
    }




}


