package Dao;
import Model.Tipoanexo;
import java.util.List;

/**
 *
 * @author Dajoh
 */
public interface TipoAnexoDao {
    public List<Tipoanexo> mostrarTipoAnexo();
    public void insertarTipoAnexo(Tipoanexo tipoanexo);
    public void modificarTipoAnexo (Tipoanexo tipoanexo);
    public void eliminarTipoAnexo (Tipoanexo tipoanexo);
}
