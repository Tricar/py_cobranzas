package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("claseconverter")
public class claseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String estado = "";
        if (value != null) {
            estado = (String) value;
            switch (estado) {
                case "FR":
                    estado = "FORMAL";
                    break;
                case "IF":
                    estado = "INFORMAL";
                    break;                
            }
        }
        return estado;
    }

}
