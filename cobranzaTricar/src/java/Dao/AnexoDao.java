package Dao;
import Model.Anexo;
import java.util.List;

/**
 *
 * @author Dajoh
 */
public interface AnexoDao {
    
    public List<Anexo> mostrarAnexo();
    public void insertarAnexo(Anexo anexo);
    public void modificarAnexo (Anexo anexo);
    public void eliminarAnexo (Anexo anexo);
    
}
