package Converter;

import Dao.AnexoDaoImplements;
import Model.Anexo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author master
 */
@FacesConverter("avalconverter")
public class avalConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String nombre = "";
        Anexo anexo = new Anexo();
        if (value != null) {            
            AnexoDaoImplements dao = new AnexoDaoImplements();
            anexo = dao.devolverNombre(Integer.parseInt(value.toString()));
        }
        nombre = anexo.getNombres();
        return nombre;
    }
}