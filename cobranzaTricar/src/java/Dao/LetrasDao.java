package Dao;

import Model.Credito;
import Model.Letras;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface LetrasDao{
    public List<Letras> mostrarLetras();
    public List<Letras> mostrarLetrasXCred(Credito credito);
    public void insertarLetra(Letras letras);
    public void modificarLetra (Letras letras);
    public void eliminarLetra (Letras letras);
    public boolean registrar(Session session, Letras letras)throws Exception;    
}
