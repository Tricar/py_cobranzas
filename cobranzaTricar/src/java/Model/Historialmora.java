package Model;
// Generated 21/04/2016 10:03:10 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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

@Entity
@Table(name = "historialmora", schema = "dbo", catalog = "cobranzas"
)
public class Historialmora implements java.io.Serializable {

    private Integer idhistorialmora;
    private Letras letras;
    private Date fecreg;
    private BigDecimal monto;
    private BigDecimal montoanterior;
    private Integer usuario;
    private Long diffdays;

    public Historialmora() {
    }

    public Historialmora(Integer idhistorialmora) {
        this.idhistorialmora = idhistorialmora;
    }

    public Historialmora(Integer idhistorialmora, Letras letras, Date fecreg, BigDecimal monto, BigDecimal montoanterior, Integer usuario, Long diffdays) {
        this.idhistorialmora = idhistorialmora;
        this.letras = letras;
        this.fecreg = fecreg;
        this.monto = monto;
        this.montoanterior = montoanterior;
        this.usuario = usuario;
        this.diffdays = diffdays;

    }

    @Id

    @Column(name = "idhistorialmora", unique = true, nullable = false)
    public Integer getIdhistorialmora() {
        return this.idhistorialmora;
    }

    public void setIdhistorialmora(Integer idhistorialmora) {
        this.idhistorialmora = idhistorialmora;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idletras")
    public Letras getLetras() {
        return this.letras;
    }

    public void setLetras(Letras letras) {
        this.letras = letras;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecreg", length = 23)
    public Date getFecreg() {
        return this.fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

    @Column(name = "monto", precision = 17)
    public BigDecimal getMonto() {
        return this.monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @Column(name = "montoanterior", precision = 17)
    public BigDecimal getMontoanterior() {
        return this.montoanterior;
    }

    public void setMontoanterior(BigDecimal montoanterior) {
        this.montoanterior = montoanterior;
    }

    @Column(name = "usuario")
    public Integer getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
    
    @Column(name = "diffdays")
    public Long getDiffdays() {
        return this.diffdays;
    }

    public void setDiffdays(Long diffdays) {
        this.diffdays = diffdays;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idhistorialmora != null)
                ? idhistorialmora.equals(((Historialmora) other).idhistorialmora)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idhistorialmora != null)
                ? (getClass().hashCode() + idhistorialmora.hashCode())
                : super.hashCode();
    }

}