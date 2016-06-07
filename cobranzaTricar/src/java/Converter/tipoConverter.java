package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("tipoconverter")
public class tipoConverter implements Converter {

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
                case "DP":
                    estado = "DEPENDIENTE";
                    break;
                case "IN":
                    estado = "INDEPENDIENTE";
                    break;
                case "TR":
                    estado = "TRANSPORTISTA";
                    break;
            }
        }
        return estado;
    }

}
