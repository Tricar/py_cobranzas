
package Dao;

import Model.Vehiculo;
import java.util.List;

public interface VehiculoDao {
    public List<Vehiculo> mostrarVehiculo();
    public void insertarVehiculo(Vehiculo vehiculo);
    public void modificarVehiculo (Vehiculo vehiculo);
    public void eliminarVehiculo (Vehiculo vehiculo);
    public List<Vehiculo> buscarxNombre(String nombre);
}
