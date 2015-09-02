/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validatorVacio")

public class ValidatorVacio implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        
        HtmlInputText htmlinputtext = (HtmlInputText) component;
        
        if (htmlinputtext.getLabel()==null || htmlinputtext.getLabel().trim().equals("")) {
            label = htmlinputtext.getId();
        } else {
            label = htmlinputtext.getLabel();
        }
        if (value.toString().trim().equals("")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", label+": es un campo obligatorio"));
        }
    }
    
}