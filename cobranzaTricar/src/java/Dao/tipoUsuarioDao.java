package Dao;
import Model.TipoAnexo;
import java.util.List;

/**
 *
 * @author Dajoh
 */
public interface tipoUsuarioDao {
    public List<TipoAnexo> mostrarTipoAnexo();
    public void insertarTipoAnexo(TipoAnexo tipoanexo);
    public void modificarTipoAnexo (TipoAnexo tipoanexo);
    public void eliminarTipoAnexo (TipoAnexo tipoanexo);
}
