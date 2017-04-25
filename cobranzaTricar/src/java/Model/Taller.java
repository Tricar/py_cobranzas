package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1

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
 * Operacion generated by hbm2java
 */
@Entity
@Table(name = "taller",
         schema = "dbo",
         catalog = "cobranzas"
)
public class Taller implements java.io.Serializable {

    private Integer idtaller;
    private Anexo anexo;
    private Vehiculo vehiculo;
    private Vehiculon vehiculon;
    private Integer idusuario;
    private String codigo;
    private Date fechaini;
    private Date fechafin;
    private String estado;
    private String condicion;
    private Date created;

    public Taller() {
    }

    public Taller(Integer idtaller) {
        this.idtaller = idtaller;
    }

    public Taller(Integer idtaller, Anexo anexo, Vehiculo vehiculo, Vehiculon vehiculon, Integer idusuario, String codigo, Date fechaini, Date fechafin, String estado, String condicion, Date created) {
        this.idtaller = idtaller;
        this.anexo = anexo;
        this.vehiculo = vehiculo;
        this.vehiculon = vehiculon;
        this.idusuario = idusuario;
        this.codigo = codigo;
        this.fechaini = fechaini;        
        this.fechafin = fechafin;
        this.estado = estado;
        this.condicion = condicion;
        this.created = created;
    }

    @Id

    @Column(name = "idtaller", unique = true, nullable = false)
    public Integer getIdoperacion() {
        return this.idtaller;
    }

    public void setIdoperacion(Integer idtaller) {
        this.idtaller = idtaller;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idanexo")
    public Anexo getAnexo() {
        return this.anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idvehiculo")
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idvehiculon")
    public Vehiculon getVehiculon() {
        return this.vehiculon;
    }

    public void setVehiculon(Vehiculon vehiculon) {
        this.vehiculon = vehiculon;
    }

    @Column(name = "idusuario")
    public Integer getIdusuario() {
        return this.idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    @Column(name = "codigo", length = 20)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaini", length = 23)
    public Date getFechaini() {
        return this.fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechafin", length = 23)
    public Date getFechafin() {
        return this.fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    @Column(name = "estado", length = 10)
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", length = 23)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "condicion", length = 1000)
    public String getCondicion() {
        return this.condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idtaller != null)
                ? idtaller.equals(((Taller) other).idtaller)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idtaller != null)
                ? (getClass().hashCode() + idtaller.hashCode())
                : super.hashCode();
    }

}
