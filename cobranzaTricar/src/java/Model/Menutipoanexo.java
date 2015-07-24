package Model;
// Generated 24/07/2015 05:48:55 PM by Hibernate Tools 4.3.1


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
 * Menutipoanexo generated by hbm2java
 */
@Entity
@Table(name="menutipoanexo"
    ,schema="dbo"
    ,catalog="cobranzas"
)
public class Menutipoanexo  implements java.io.Serializable {


     private int idmenutipo;
     private Menu menu;
     private Tipoanexo tipoanexo;
     private Date fechareg;

    public Menutipoanexo() {
    }

	
    public Menutipoanexo(int idmenutipo, Menu menu, Tipoanexo tipoanexo) {
        this.idmenutipo = idmenutipo;
        this.menu = menu;
        this.tipoanexo = tipoanexo;
    }
    public Menutipoanexo(int idmenutipo, Menu menu, Tipoanexo tipoanexo, Date fechareg) {
       this.idmenutipo = idmenutipo;
       this.menu = menu;
       this.tipoanexo = tipoanexo;
       this.fechareg = fechareg;
    }
   
     @Id 

    
    @Column(name="idmenutipo", unique=true, nullable=false)
    public int getIdmenutipo() {
        return this.idmenutipo;
    }
    
    public void setIdmenutipo(int idmenutipo) {
        this.idmenutipo = idmenutipo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmenu", nullable=false)
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idtipoanexo", nullable=false)
    public Tipoanexo getTipoanexo() {
        return this.tipoanexo;
    }
    
    public void setTipoanexo(Tipoanexo tipoanexo) {
        this.tipoanexo = tipoanexo;
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


