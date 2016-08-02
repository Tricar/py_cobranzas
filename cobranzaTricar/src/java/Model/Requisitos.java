package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Conceptos generated by master
 */
@Entity
@Table(name = "requisitos", schema = "dbo", catalog = "cobranzas"
)
public class Requisitos implements java.io.Serializable {

    private Integer idrequisitos;
    private Credito credito;
    private String copdni;
    private String copdniaval;
    private String coptitulo;
    private String coprecibo;
    private String recibagua;    
    private String recibluz;    
    private String croq;    
    private String copdnicon;    
    private String otros;    
    

    public Requisitos() {
    }

    public Requisitos(Integer idrequisitos) {
        this.idrequisitos = idrequisitos;
    }

    public Requisitos(Integer idrequisitos, Credito credito, String copdni, String copdniaval, String coptitulo, String coprecibo, String recibagua, String recibluz, String croq, String copdnicon, String otros)  {
        this.idrequisitos = idrequisitos;
        this.credito = credito;
        this.copdni = copdni;
        this.copdniaval = copdniaval;
        this.coptitulo = coptitulo;
        this.coprecibo = coprecibo;
        this.recibagua = recibagua;        
        this.recibluz = recibluz;        
        this.croq = croq;        
        this.copdnicon = copdnicon;        
        this.otros = otros;        
    }

    @Id

    @Column(name = "idrequisitos", unique = true, nullable = false)
    public Integer getIdrequisitos() {
        return this.idrequisitos;
    }

    public void setIdrequisitos(Integer idconceptos) {
        this.idrequisitos = idconceptos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idventa")
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
    
    @Column(name = "coprecibo", length = 50)
    public String getCoprecibo() {
        return this.coprecibo;
    }    

    public void setCoprecibo(String coprecibo) {
        this.coprecibo = coprecibo;
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
    
    @Column(name = "copdnicon", length = 50)
    public String getCopdnicon() {
        return this.copdnicon;
    }    

    public void setCopdnicon(String copdnicon) {
        this.copdnicon = copdnicon;
    }
    
    @Column(name = "otros", length = 50)
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
