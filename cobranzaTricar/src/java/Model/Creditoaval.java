package Model;
// Generated Feb 22, 2017 8:23:40 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Creditoaval generated by hbm2java
 */
@Entity
@Table(name="creditoaval"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Creditoaval  implements java.io.Serializable {


     private Integer idcreditoaval;
     private Anexo anexo;
     private Credito credito;

    public Creditoaval() {
    }

	
    public Creditoaval(int idcreditoaval) {
        this.idcreditoaval = idcreditoaval;
    }
    public Creditoaval(int idcreditoaval, Anexo anexo, Credito credito) {
       this.idcreditoaval = idcreditoaval;
       this.anexo = anexo;
       this.credito = credito;
    }
   
     @Id 

    
    @Column(name="idcreditoaval", unique=true, nullable=false)
    public Integer getIdcreditoaval() {
        return this.idcreditoaval;
    }
    
    public void setIdcreditoaval(Integer idcreditoaval) {
        this.idcreditoaval = idcreditoaval;
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
    @JoinColumn(name="idventa")
    public Credito getCredito() {
        return this.credito;
    }
    
    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.idcreditoaval;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Creditoaval other = (Creditoaval) obj;
        if (this.idcreditoaval != other.idcreditoaval) {
            return false;
        }
        return true;
    }

}
