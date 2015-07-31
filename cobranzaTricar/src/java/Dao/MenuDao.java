package Dao;

import Model.Menu;
import java.util.List;

/**
 *
 * @author master
 */
public interface MenuDao {
    public List<Menu> mostrarMenu();
    public void insertarMenu(Menu menu);
    public void modificarMenu(Menu menu);
    public void eliminarMenu(Menu menu);
}
