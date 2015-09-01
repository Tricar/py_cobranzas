package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("marcaconverter")
public class marcaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String marca ="";
        if (value != null){
            marca = (String) value;
            switch (marca) {
                case "T": marca = "Tricar";
                    break;
                case "V": marca = "Velorex";
                    break;                                
            }
        }
        return marca;
    }
    
}
