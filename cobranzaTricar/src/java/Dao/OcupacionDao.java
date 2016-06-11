/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Anexo;
import Model.Credito;
import Model.Ocupacion;
import java.util.List;

/**
 *
 * @author Dajoh
 */
public interface OcupacionDao {
    public void insertarOcupacion(Ocupacion ocupacion);
    public void modificarOcupacion (Ocupacion ocupacion);
    public void eliminarOcupacion (Ocupacion ocupacion);
    public List<Ocupacion> mostrarOcupaciones();
    public List<Ocupacion> ocupacionesxIdanexo(Anexo anexo);
    public Ocupacion verifyIdocup(Integer idocupacion);
    public List<Ocupacion> ocupacionesxIdventa(Credito credito);
    public void delete(Ocupacion ocup);
}
