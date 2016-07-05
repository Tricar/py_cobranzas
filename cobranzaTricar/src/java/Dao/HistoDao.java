package Dao;

import Model.Credito;
import Model.Letras;
import Model.Historialmora;
import java.util.List;

/**
 *
 * @author master
 */
public interface HistoDao {
    public List<Historialmora> mostrarHistoriales();
    public List<Historialmora> mostrarHistxCredito(Credito credito);
    public List<Historialmora> mostrarHistxLetras(Letras letra);
    public void insertarHist(Historialmora historial);
    public void modificarHist (Historialmora historial);
    public void eliminarHist (Historialmora historial);
    public Historialmora getbyId(Integer idhistorial);
}
