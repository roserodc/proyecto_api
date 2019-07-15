package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.GuiaEntrenamiento;
import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Rutina;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerPlan {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
	@EJB
	public ManagerRutina managerRutina;
	@EJB
	public ManagerGuiaEntrenamiento managerGuiaEntrenamiento;
	
    public ManagerPlan() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Plane>findAll(){
    	String c = "SELECT c FROM Plane c order by pl_id";
    	Query q = em.createQuery(c,Plane.class);
    	return q.getResultList();
    }
    
    public Plane findById(int id) {
    	return em.find(Plane.class, id);
    }
    
    public Plane insertar(Plane plan,
    		Integer idguia) {
    	GuiaEntrenamiento ge;
      	ge=managerGuiaEntrenamiento.findById(idguia);
    	Plane pl = new Plane();
    	pl.setPlTipo(plan.getPlTipo());
    	pl.setGuiaEntrenamiento(ge);
//    	pl.setRutina(rut);
    	em.persist(pl);
    	return pl;
    }
    
    public void eliminar (Integer id) {
    	Plane pl = findById(id);
    	if (pl!=null) {
    		em.remove(pl);
		}
    }
    
    public void actualizar (Plane plan) throws  Exception{
    	Plane pl = findById(plan.getPlId());
    	if (pl==null) 
			throw new Exception("No existe");
    	pl.setGuiaEntrenamiento(plan.getGuiaEntrenamiento());
    	pl.setPlTipo(plan.getPlTipo());
    	pl.setRutina(plan.getRutina());
		em.merge(pl);
    }

}
