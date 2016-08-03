package Dao;

import Model.Anexo;
import Model.Requisitos;
import Model.Credito;
import java.util.List;

/**
 *
 * @author master
 */
public interface RequisitosDao{
    public List<Requisitos> mostrarRequisitos();
    public Requisitos mostrarRequisitosXCred(Credito credito);    
    public void insertarRequisito(Requisitos requisitos);
    public void modificarRequisito (Requisitos requisitos);
    public void eliminarRequisito (Requisitos requisitos);    
    public Requisitos veryId(Integer idrequisitos);
    public Requisitos veryIdCredito(Credito credito);
}

