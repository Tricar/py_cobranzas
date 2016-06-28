package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@SessionScoped
public class UrlBean {

    public String menu(){        
        return "case2";
    }
    
    public UrlBean() {
    }
    
}
