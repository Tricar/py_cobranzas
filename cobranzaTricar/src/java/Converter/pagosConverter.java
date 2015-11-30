/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("pagosconverter")
public class pagosConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String estpagos = "";
        if (value != null) {
            estpagos = (String) value;
            switch (estpagos) {
                case "NC":
                    estpagos = "NOTA DE CREDITO";
                    break;
                case "LE":
                    estpagos = "PAGO NORMAL";
                    break;
                case "ND":
                    estpagos = "NOTA DE DEBITO";
                    break;                
            }
        }
        return estpagos;
    }
    
}
