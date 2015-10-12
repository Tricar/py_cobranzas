package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("distritoconverter")
public class distritoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String tienda ="";
        if (value != null){
            tienda = (String) value;
            switch (tienda) {
                case "CA": tienda = "CALLERIA";
                    break;
                case "YA": tienda = "YARINACOCHA";
                    break;
                case "MA": tienda = "MANANTAY";
                    break;
                case "CV": tienda = "CAMPO VERDE";
                    break;
                case "NR": tienda = "NUEVA REQUENA";
                    break;
                case "NE": tienda = "NESHUYA";
                    break;
                case "CU": tienda = "CURIMANA";
                    break;
                case "TV": tienda = "TOURNAVISTA";
                    break;
                case "IR": tienda = "IRAZOLA";
                    break;
            }
        }
        return tienda;
    }
    
}
