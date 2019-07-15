package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Club;
import proyecto_api.model.entities.Nivel;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerNivel {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerNivel() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Nivel>findAll(){
    	String c = "SELECT c FROM Nivel c order by ni_id";
    	Query q = em.createQuery(c,Nivel.class);
    	return q.getResultList();
    }
    
    public Nivel findById(int id) {
    	return em.find(Nivel.class, id);
    }
    
    public Nivel insertar(Nivel nivel) {
    	Nivel ni = new Nivel();
    	ni.setNiDescripcion(nivel.getNiDescripcion());
    	em.persist(ni);
    	return ni;
    }
    
    public void eliminar (Integer id) {
    	Nivel ni = findById(id);
    	if (ni!=null) {
    		em.remove(ni);
		}
    }
    
    public void actualizar (Nivel nivel) throws  Exception{
    	Nivel ni = findById(nivel.getNiId());
    	if (ni==null) 
			throw new Exception("No existe");
    	ni.setNiDescripcion(nivel.getNiDescripcion());
		em.merge(ni);
    }

}
