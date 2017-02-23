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
@FacesConverter("estpagoconverter")
public class estadopagoConverter implements Converter {

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
                case "C":
                    estado = "PAGADO";
                    break;
                case "X":
                    estado = "ANULADO";
                    break;
                case "E":
                    estado = "EMITIDO";
                    break;
                case "J":
                    estado = "PROCESADO";
                    break;
            }
        }
        return estado;
    }
}
