package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("tiendaconverter")
public class tiendaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String tienda = "";
        if (value != null) {
            tienda = (String) value;
            switch (tienda) {
                case "V1":
                    tienda = "VX1";
                    break;
                case "V2":
                    tienda = "VX2";
                    break;
                case "YA":
                    tienda = "YARINA";
                    break;
            }
        }
        return tienda;
    }

}
