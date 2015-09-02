package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("tvehiculo")
public class tVehiculoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String tvehiculo ="";
        if (value != null){
            tvehiculo = (String) value;
            switch (tvehiculo) {
                case "MX": tvehiculo = "MTX";
                    break;
                case "ML": tvehiculo = "ML";
                    break;
                case "MF": tvehiculo = "MTF";
                    break;                
            }
        }
        return tvehiculo;
    }
    
}
