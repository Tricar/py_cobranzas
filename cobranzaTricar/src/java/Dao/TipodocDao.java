package Dao;

import Model.Tipodoc;
import java.util.List;

/**
 *
 * @author master
 */
public interface TipodocDao {
    public List<Tipodoc> mostrarTipoDocs();
    public List<Tipodoc> mostrarxTipo(String tipodoc, String tipos);
    public void insertarTipodoc(Tipodoc tipodoc);
    public void modificarTipodoc (Tipodoc tipodoc);
    public void eliminarTipodoc (Tipodoc tipodoc);    
}
