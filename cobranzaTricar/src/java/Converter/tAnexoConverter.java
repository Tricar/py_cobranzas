package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("tanexoconverter")
public class tAnexoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String tanexo ="";
        if (value != null){
            tanexo = (String) value;
            switch (tanexo) {
                case "AD": tanexo = "Administrador";
                    break;
                case "CL": tanexo = "Cliente";
                    break;
                case "VE": tanexo = "Vendedor";
                    break;
                case "GE": tanexo = "Gestor";
                    break;
            }
        }
        return tanexo;
    }
    
}
