package Model;
// Generated 13/08/2015 11:16:31 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * Sysdiagrams generated by hbm2java
 */
@Entity
@Table(name="sysdiagrams"
    ,schema="dbo"
    ,catalog="cobranzas"
    , uniqueConstraints = @UniqueConstraint(columnNames={"principal_id", "name"}) 
)
public class Sysdiagrams  implements java.io.Serializable {


     private int diagramId;
     private Integer version;
     private String name;
     private int principalId;
     private byte[] definition;

    public Sysdiagrams() {
    }

	
    public Sysdiagrams(int diagramId, String name, int principalId) {
        this.diagramId = diagramId;
        this.name = name;
        this.principalId = principalId;
    }
    public Sysdiagrams(int diagramId, String name, int principalId, byte[] definition) {
       this.diagramId = diagramId;
       this.name = name;
       this.principalId = principalId;
       this.definition = definition;
    }
   
     @Id 

    
    @Column(name="diagram_id", unique=true, nullable=false)
    public int getDiagramId() {
        return this.diagramId;
    }
    
    public void setDiagramId(int diagramId) {
        this.diagramId = diagramId;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    
    @Column(name="name", nullable=false, length=128)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="principal_id", nullable=false)
    public int getPrincipalId() {
        return this.principalId;
    }
    
    public void setPrincipalId(int principalId) {
        this.principalId = principalId;
    }

    
    @Column(name="definition")
    public byte[] getDefinition() {
        return this.definition;
    }
    
    public void setDefinition(byte[] definition) {
        this.definition = definition;
    }




}


