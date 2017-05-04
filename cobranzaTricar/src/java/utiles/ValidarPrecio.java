package utiles;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ValidarPrecio {

    public boolean validar(String modelo, BigDecimal precio1) {
        String model = modelo;
        boolean sw = true;
        if (model != null) {
            switch (model) {
                case "VX150":
                    if (precio1.compareTo(BigDecimal.valueOf(5570.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio no puede ser menor a S/. 5570.00"));
                        sw = false;
                    }
                    break;
                case "MTC VX150":
                    if (precio1.compareTo(BigDecimal.valueOf(6390.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX200-C":
                    if (precio1.compareTo(BigDecimal.valueOf(6490.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX250ZH-E":
                    if (precio1.compareTo(BigDecimal.valueOf(7600.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX250ZH-M":
                    if (precio1.compareTo(BigDecimal.valueOf(8690.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX250ZH-1":
                    if (precio1.compareTo(BigDecimal.valueOf(8690.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX110":
                    if (precio1.compareTo(BigDecimal.valueOf(2750.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX110-A":
                    if (precio1.compareTo(BigDecimal.valueOf(2490.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX110-7":
                    if (precio1.compareTo(BigDecimal.valueOf(2990.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX110-9":
                    if (precio1.compareTo(BigDecimal.valueOf(2890.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX125-2T":
                    if (precio1.compareTo(BigDecimal.valueOf(3100.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX150-C":
                    if (precio1.compareTo(BigDecimal.valueOf(2820.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio no puede ser menor a S/. 5570.00"));
                        sw = false;
                    }
                    break;
                case "TX150-R":
                    if (precio1.compareTo(BigDecimal.valueOf(3990.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX150-8":
                    if (precio1.compareTo(BigDecimal.valueOf(3000.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX150-25":
                    if (precio1.compareTo(BigDecimal.valueOf(4400.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX150GY-11B":
                    if (precio1.compareTo(BigDecimal.valueOf(3690.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX150GY-11A":
                    if (precio1.compareTo(BigDecimal.valueOf(4890.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX150GY-11":
                    if (precio1.compareTo(BigDecimal.valueOf(3890.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX200GY-V2":
                    if (precio1.compareTo(BigDecimal.valueOf(4590.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX150":
                    if (precio1.compareTo(BigDecimal.valueOf(4670.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio no puede ser menor a S/. 4670.00"));
                        sw = false;
                    }
                    break;
                case "GS-125":
                    if (precio1.compareTo(BigDecimal.valueOf(3090.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX110 LYRA":
                    if (precio1.compareTo(BigDecimal.valueOf(3090.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "VX200ZH-1":
                    if (precio1.compareTo(BigDecimal.valueOf(6370.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio no puede ser menor a S/. 6370.00"));
                        sw = false;
                    }
                    break;
                case "VX200ZH-1/R":
                    if (precio1.compareTo(BigDecimal.valueOf(6770.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio no puede ser menor a S/. 6770.00"));
                        sw = false;
                    }
                    break;
                case "TX150N":
                    if (precio1.compareTo(BigDecimal.valueOf(7290.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX125":
                    if (precio1.compareTo(BigDecimal.valueOf(2890.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                case "TX110":
                    if (precio1.compareTo(BigDecimal.valueOf(2070.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio no puede ser menor a S/. 2070.00"));
                        sw = false;
                    }
                    break;
                case "TX150GY-11B":
                    if (precio1.compareTo(BigDecimal.valueOf(3890.00)) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este producto no está en promoción"));
                        sw = false;
                    }
                    break;
                default:
                    sw = true;
                    break;
            }
        }
        return sw;
    }

}
