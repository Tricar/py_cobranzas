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
    public void insertarVehiculo(Vehiculo vehiculo);
    public void modificarVehiculo (Vehiculo vehiculo);
    public void eliminarVehiculo (Vehiculo vehiculo);
}
