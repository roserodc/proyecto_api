package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Estado;

/**
 * Session Bean implementation class ManagerEstados
 */
@Stateless
@LocalBean
public class ManagerEstados {
	@PersistenceContext
	private EntityManager em;
	
    public ManagerEstados() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Estado>findAll(){
    	String e= "SELECT e FROM Estado e order by estId";
    	Query q=em.createQuery(e, Estado.class);
    	return q.getResultList();
    }

    public Estado findById(int id) {
    	return em.find(Estado.class, id);
    }
    
    public Estado insertar(Estado estado) {
    	Estado est=new Estado();
    	est.setEstDescripcion(estado.getEstDescripcion());
    	em.persist(est);
    	return est;
    }
    
    public void eliminar (Integer id) {
    	Estado est=findById(id);
    	if (est!=null) {
			em.remove(est);
		}
    }
    
    public void actualizar(Estado estado) throws Exception {
    	Estado est=findById(estado.getEstId());
    	if(est==null)
    		throw new Exception("No existe");
    	est.setEstDescripcion(estado.getEstDescripcion());
    	em.merge(est);
    }

}
