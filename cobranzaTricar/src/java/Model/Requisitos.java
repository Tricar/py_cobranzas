package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Requisitos generated by hbm2java
 */
@Entity
@Table(name = "requisitos",
         schema = "dbo",
         catalog = "cobranzas"
)
public class Requisitos implements java.io.Serializable {

    private Integer idrequisitos;
    private Credito credito;
    private String copdni;
    private String copdnicon;
    private String copdniaval;
    private String coptitulo;
    private String recibagua;
    private String recibluz;
    private String croq;
    private String coprecibo;
    private String otros;

    public Requisitos() {
    }

    public Requisitos(Integer idrequisitos, Credito credito) {
        this.idrequisitos = idrequisitos;
        this.credito = credito;
    }

    public Requisitos(Integer idrequisitos, Credito credito, String copdni, String copdnicon, String copdniaval, String coptitulo, String recibagua, String recibluz, String croq, String coprecibo, String otros) {
        this.idrequisitos = idrequisitos;
        this.credito = credito;
        this.copdni = copdni;
        this.copdnicon = copdnicon;
        this.copdniaval = copdniaval;
        this.coptitulo = coptitulo;
        this.recibagua = recibagua;
        this.recibluz = recibluz;
        this.croq = croq;
        this.coprecibo = coprecibo;
        this.otros = otros;
    }

    @Id

    @Column(name = "idrequisitos", unique = true, nullable = false)
    public Integer getIdrequisitos() {
        return this.idrequisitos;
    }

    public void setIdrequisitos(Integer idrequisitos) {
        this.idrequisitos = idrequisitos;
    }

    @Column(name = "idventa", nullable = false)
    public Credito getCredito() {
        return this.credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    @Column(name = "copdni", length = 50)
    public String getCopdni() {
        return this.copdni;
    }

    public void setCopdni(String copdni) {
        this.copdni = copdni;
    }

    @Column(name = "copdnicon", length = 50)
    public String getCopdnicon() {
        return this.copdnicon;
    }

    public void setCopdnicon(String copdnicon) {
        this.copdnicon = copdnicon;
    }

    @Column(name = "copdniaval", length = 50)
    public String getCopdniaval() {
        return this.copdniaval;
    }

    public void setCopdniaval(String copdniaval) {
        this.copdniaval = copdniaval;
    }

    @Column(name = "coptitulo", length = 50)
    public String getCoptitulo() {
        return this.coptitulo;
    }

    public void setCoptitulo(String coptitulo) {
        this.coptitulo = coptitulo;
    }

    @Column(name = "recibagua", length = 50)
    public String getRecibagua() {
        return this.recibagua;
    }

    public void setRecibagua(String recibagua) {
        this.recibagua = recibagua;
    }

    @Column(name = "recibluz", length = 50)
    public String getRecibluz() {
        return this.recibluz;
    }

    public void setRecibluz(String recibluz) {
        this.recibluz = recibluz;
    }

    @Column(name = "croq", length = 50)
    public String getCroq() {
        return this.croq;
    }

    public void setCroq(String croq) {
        this.croq = croq;
    }

    @Column(name = "coprecibo", length = 50)
    public String getCoprecibo() {
        return this.coprecibo;
    }

    public void setCoprecibo(String coprecibo) {
        this.coprecibo = coprecibo;
    }

    @Column(name = "otros", length = 100)
    public String getOtros() {
        return this.otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idrequisitos != null)
                ? idrequisitos.equals(((Requisitos) other).idrequisitos)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (idrequisitos != null)
                ? (getClass().hashCode() + idrequisitos.hashCode())
                : super.hashCode();
    }

}
