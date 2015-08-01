
package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@RequestScoped
public class menuBean {

    /**
     * Creates a new instance of menuBean
     */
    public menuBean() {
    }
    
    public void save() {
        
    }
     
    public void update() {
        
    }
     
    public void delete() {
        
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }
    
}
