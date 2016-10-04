package Dao;

import Model.Visitas;
import Model.Visitas;
import java.util.Date;
import java.util.List;

/**
 *
 * @author master
 */
public interface VisitasDao {
    public List<Visitas> mostrarVisitas();    
    public void insertarVisitas(Visitas visitas);
    public void modificarVisitas (Visitas visitas);
    public void eliminarVisitas (Visitas visitas);
    public Visitas getbyId(Integer idvisitas);
    public List<Visitas> historial (Date fecha1, Date fecha2);
}
