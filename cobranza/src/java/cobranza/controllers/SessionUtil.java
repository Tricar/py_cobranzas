/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.controllers;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 
 */
public class SessionUtil {

    // Se crean las variables de sesion.
    public static void addSession(Integer userId, String userNombre, Integer tipo, Integer Anexo, String userTipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(true);

        sesion.setAttribute("userLog", userId);
        sesion.setAttribute("userNombre", userNombre);
        sesion.setAttribute("userAnexoId", Anexo);
        sesion.setAttribute("userTipoId", tipo);
        sesion.setAttribute("userTipo", userTipo);
    }

    // Se cierra la sesion.
    public static void closeSession() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) ctx.getSession(false)).invalidate();
    }

    // Recupera el código del usuario logueado.
    public static Integer getUserLog() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        Integer userLog = (Integer)sesion.getAttribute("userLog");
        return userLog;
    }

    // Recupera el nombre del usuario logueado.
    public static String getUserNombreLog() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        String nombre = (String)sesion.getAttribute("userNombre");
        return nombre;
    }

    // Recupera el tipo de usuario del usuario logueado (el ID).
    public static Integer getIdUserAnexoLog() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        Integer tipo = (Integer)sesion.getAttribute("userAnexoId");
        return tipo;
    }

    // Recupera el tipo de usuario del usuario logueado (el nombre).
    public static String getIdUserTipoLog() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        String tipo = (String)sesion.getAttribute("userTipoId");
        return tipo;
    }
    
    public static String getUserTipoLog() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        String tipo = (String)sesion.getAttribute("userTipo");
        return tipo;
    }

    // Nombre de la página actual que se está visitando
    public static String getPagina() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        return ctx.getRequestPathInfo();
    }

    // Método para redirigir a página del Sitio.
    public static void redirectTo(String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

        try { ctx.redirect(ctxPath + url); }
        catch (IOException ex) {  }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

}
