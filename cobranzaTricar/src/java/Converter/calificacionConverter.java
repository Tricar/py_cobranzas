package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("califconverter")
public class calificacionConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String estado ="";
        if (value != null){
            estado = (String) value;
            switch (estado) {
                case "PN": estado = "PENDIENTE";
                    break;
                case "CN": estado = "CANCELADO";
                    break;
                case "VN": estado = "VENCIDO";
                    break;                                
            }
        }
        return estado;
    }
    
}
