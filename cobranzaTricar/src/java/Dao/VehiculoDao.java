/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Vehiculo;
import java.util.List;

/**
 *
 * @author master
 */
public interface VehiculoDao {
    public List<Vehiculo> mostrarVehiculo();
    public void insertarTipoVehiculo(Vehiculo vehiculo);
    public void modificarTipoVehiculo (Vehiculo vehiculo);
    public void eliminarTipoVehiculo (Vehiculo vehiculo);
}
