package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.GuiaEntrenamiento;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerGuiaEntrenamiento {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerGuiaEntrenamiento() {
        // TODO Auto-generated constructor stub
    }
    
    public List<GuiaEntrenamiento>findAll(){
    	String c = "SELECT c FROM GuiaEntrenamiento c order by ge_id";
    	Query q = em.createQuery(c,GuiaEntrenamiento.class);
    	return q.getResultList();
    }
    
    public GuiaEntrenamiento findById(int id) {
    	return em.find(GuiaEntrenamiento.class, id);
    }
    
    public GuiaEntrenamiento insertar(GuiaEntrenamiento guiaEntrenamiento) {
    	GuiaEntrenamiento ge = new GuiaEntrenamiento();
    	ge.setGeDescripcion(guiaEntrenamiento.getGeDescripcion());
//    	ge.setPlanes(guiaEntrenamiento.getPlanes());
//    	ge.setPeticiones(guiaEntrenamiento.getPeticiones());
    	em.persist(ge);
    	return ge;
    }
    
    public void eliminar (Integer id) {
    	GuiaEntrenamiento ge = findById(id);
    	if (ge!=null) {
    		em.remove(ge);
		}
    }
    
    public void actualizar (GuiaEntrenamiento guiaEntrenamiento) throws  Exception{
    	GuiaEntrenamiento ge = findById(guiaEntrenamiento.getGeId());
    	if (ge==null) 
			throw new Exception("No existe");
    	ge.setGeDescripcion(guiaEntrenamiento.getGeDescripcion());
		em.merge(ge);
    }

}
