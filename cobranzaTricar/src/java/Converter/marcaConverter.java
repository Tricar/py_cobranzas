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
        String marca = "";        
        if (value != null){            
            switch (value.toString()) {
                case "T": marca = "TRICAR";
                    break;
                case "V": marca = "VELOREX";
                    break;                                
            }
        }
        return marca;
    }
    
}
