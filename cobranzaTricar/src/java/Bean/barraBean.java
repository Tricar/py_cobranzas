package Bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class barraBean implements Serializable{
    
    private String actuacion;
 
    private String recibo;
 
    private String codigo;
 
    @PostConstruct
    public void iniciar(){
        this.actuacion = "2";
        this.codigo = "55";
        this.recibo = "88";
    }
 
 
    public String getActuacion() {
        return actuacion;
    }
 
    public void setActuacion(String actuacion) {
        this.actuacion = actuacion;
    }
 
    public String getRecibo() {
        return recibo;
    }
 
    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }
 
    public String getCodigo() {
        return codigo;
    }
 
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
    public void generar(){
        this.codigo = this.actuacion + this.recibo;
    }
    
}
