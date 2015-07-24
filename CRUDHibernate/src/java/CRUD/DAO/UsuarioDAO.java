/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD.DAO;

import CRUD.Entidad.Usuario;

/**
 *
 * @author Sistemas2
 */
public interface UsuarioDAO {
    
    public Usuario findByUsuario(Usuario usuario);
    public Usuario Login();
    
}
