package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("empresaconverter")
public class empresaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String empresa = "";
        if (value != null) {
            empresa = (String) value;
            switch (empresa) {
                case "TR":
                    empresa = "TRICAR";
                    break;
                case "CA":
                    empresa = "CASCO";
                    break;
                case "SE":
                    empresa = "SEDNA";
                    break;
            }
        }
        return empresa;
    }

}
