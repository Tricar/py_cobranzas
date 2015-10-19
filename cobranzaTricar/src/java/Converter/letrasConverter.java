package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("letrasconverter")
public class letrasConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String estletras = "";
        if (value != null) {
            estletras = (String) value;
            switch (estletras) {
                case "PN":
                    estletras = "PENDIENTE";
                    break;
                case "VN":
                    estletras = "VENCIDO";
                    break;
                case "CN":
                    estletras = "CANCELADO";
                    break;
                case "RF":
                    estletras = "REFINANCIADO";
                    break;
            }
        }
        return estletras;
    }

}
