package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("estcredconvert")
public class estCredConvert implements Converter{

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
                case "EM": estado = "EMITIDO";
                    break;
                case "AP": estado = "APROBADO";
                    break;                                
            }
        }
        return estado;
    }
    
}
