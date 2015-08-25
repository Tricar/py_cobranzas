/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfil;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface PerfilDao {
    
    public void actualizar(Perfil perfil);
    public Perfil buscarPorId(Integer id);
    public List<Perfil> buscarTodos();
    public void crearperfil(Perfil perfil);
    public List<Perfil> mostrarPerfil();
    public void eliminarperfil(Perfil perfil);
    
}
