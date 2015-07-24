/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD.beans;

import HibernateUtil.MyUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Sistemas2
 */
@ManagedBean
@ApplicationScoped
public class CRUDBean {

    /**
     * Creates a new instance of CRUDBean
     */
    public CRUDBean() {
    }
    
    public String getBaseUrl(){
        return MyUtil.baseurl();
    }
    
}
