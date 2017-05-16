/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Vehiculoanexo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class VehiculoanexoDaoImplements implements VehiculoanexoDao{    

    @Override
    public boolean registrar(Session session, Vehiculoanexo subfamilia) throws Exception {
        session.save(subfamilia);
        return true;
    }

    @Override
    public List<Vehiculoanexo> verTodo(Session session) throws Exception {
        String hql = "FROM Vehiculoanexo";
        Query query = session.createQuery(hql);

        List<Vehiculoanexo> lista = (List<Vehiculoanexo>) query.list();

        return lista;
    }

    @Override
    public Vehiculoanexo verByCodigo(Session session, Integer idvehiculoanexo) throws Exception {
        return (Vehiculoanexo) session.get(Vehiculoanexo.class, idvehiculoanexo);
    }

    @Override
    public Vehiculoanexo verBySubfamilia(Session session, String reporte) throws Exception {
        String hql = "FROM Vehiculoanexo WHERE reporte=:reporte";
        Query query = session.createQuery(hql);
        query.setParameter("reporte", reporte);
        Vehiculoanexo subfamily = (Vehiculoanexo) query.uniqueResult();
        return subfamily;
    }

    @Override
    public Vehiculoanexo verBySubfamiliaD(Session session, Integer idvehiculoanexo, String reporte) throws Exception {
        String hql = "FROM Vehiculoanexo WHERE idvehiculoanexo!=:idvehiculoanexo and reporte=:reporte";
        Query query = session.createQuery(hql);
        query.setParameter("idvehiculoanexo", idvehiculoanexo);
        query.setParameter("reporte", reporte);        
        Vehiculoanexo subfamily = (Vehiculoanexo) query.uniqueResult();
        return subfamily;
    }

    @Override
    public boolean modificar(Session session, Vehiculoanexo vehiculoanexo) throws Exception {
        session.update(vehiculoanexo);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Vehiculoanexo vehiculoanexo) throws Exception {
        session.delete(vehiculoanexo);
        return true;
    }

    @Override
    public List<Vehiculoanexo> verByFamilia(Session session, Integer idanexo) throws Exception {
        String hql = "FROM Vehiculoanexo WHERE idanexo=:idanexo";
        Query query = session.createQuery(hql);
        query.setParameter("idanexo", idanexo);
        List<Vehiculoanexo> lista = (List<Vehiculoanexo>) query.list();

        return lista;
    }
    
}