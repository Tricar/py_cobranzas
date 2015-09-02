/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface PerfilDao {
    
    public boolean registrar(Session session, Perfil perfil)throws Exception;
    public List<Perfil> verTodo(Session session) throws Exception;
    public Perfil verByCodigo(Session session, Integer idperfil) throws Exception;
    public Perfil verByDescripcion(Session session, String descripcion) throws Exception;
    public boolean modificar(Session session, Perfil perfil) throws Exception;
    public boolean eliminarPerfil (Session session, Perfil perfil) throws Exception;
    
}
