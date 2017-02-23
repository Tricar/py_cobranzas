package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("estadoconverter")
public class estadoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String estado ="";
        if (value != null){            
            switch (value.toString()) {
                case "N": estado = "NO DISPONIBLE";
                    break;
                case "D": estado = "DISPONIBLE";
                    break;                                
            }
        }
        return estado;
    }
    
}
