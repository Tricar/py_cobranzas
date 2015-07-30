package Dao;

import Model.Color;
import java.util.List;

/**
 *
 * @author master
 */
public interface ColorDao {
    public List<Color> mostrarColor();
    public void insertarColor(Color color);
    public void modificarColor (Color color);
    public void eliminarColor (Color color);
}
