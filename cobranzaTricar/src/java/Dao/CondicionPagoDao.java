/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Condicionpago;
import java.util.List;

/**
 *
 * @author master
 */
public interface CondicionPagoDao {
    public List<Condicionpago> mostrarCondicion();
    public void insertarCondicion(Condicionpago condicion);
    public void modificarCondicion (Condicionpago condicion);
    public void eliminarcondicion (Condicionpago condicion);
}
