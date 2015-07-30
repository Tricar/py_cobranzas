package Model;
// Generated 30/07/2015 11:43:35 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Venta generated by hbm2java
 */
@Entity
@Table(name="venta"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Venta  implements java.io.Serializable {


     private VentaId id;
     private Anexo anexo;
     private Empresa empresa;
     private Pago pago;
     private Vehiculo vehiculo;
     private Date fechareg;

    public Venta() {
    }

	
    public Venta(VentaId id, Anexo anexo, Empresa empresa, Pago pago, Vehiculo vehiculo) {
        this.id = id;
        this.anexo = anexo;
        this.empresa = empresa;
        this.pago = pago;
        this.vehiculo = vehiculo;
    }
    public Venta(VentaId id, Anexo anexo, Empresa empresa, Pago pago, Vehiculo vehiculo, Date fechareg) {
       this.id = id;
       this.anexo = anexo;
       this.empresa = empresa;
       this.pago = pago;
       this.vehiculo = vehiculo;
       this.fechareg = fechareg;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idanexo", column=@Column(name="idanexo", nullable=false) ), 
        @AttributeOverride(name="idvehiculo", column=@Column(name="idvehiculo", nullable=false) ), 
        @AttributeOverride(name="idempresa", column=@Column(name="idempresa", nullable=false) ), 
        @AttributeOverride(name="idventa", column=@Column(name="idventa", nullable=false) ), 
        @AttributeOverride(name="idpago", column=@Column(name="idpago", nullable=false) ) } )
    public VentaId getId() {
        return this.id;
    }
    
    public void setId(VentaId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idanexo", nullable=false, insertable=false, updatable=false)
    public Anexo getAnexo() {
        return this.anexo;
    }
    
    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idempresa", nullable=false, insertable=false, updatable=false)
    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpago", nullable=false, insertable=false, updatable=false)
    public Pago getPago() {
        return this.pago;
    }
    
    public void setPago(Pago pago) {
        this.pago = pago;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idvehiculo", nullable=false, insertable=false, updatable=false)
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechareg", length=23)
    public Date getFechareg() {
        return this.fechareg;
    }
    
    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }




}


