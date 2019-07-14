package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Prueba;

/**
 * Session Bean implementation class ManagerPrueba
 */
@Stateless
@LocalBean
public class ManagerPrueba {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerPrueba() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Prueba>findAll(){
    	String c = "SELECT p FROM Prueba p";
    	Query q = em.createQuery(c,Prueba.class);
    	return q.getResultList();
    }
    
    public Prueba findById(int id) {
    	return em.find(Prueba.class, id);
    }
    
    public Prueba insertar(Prueba prueba) {
    	Prueba pr = new Prueba();
    	pr.setDescripcion(prueba.getDescripcion());
    	em.persist(pr);
    	return pr;
    }
    
    public void eliminar (Integer id) {
    	Prueba pr = findById(id);
    	if (pr!=null) {
    		em.remove(pr);
		}
    }
    
    public void actualizar (Prueba prueba) throws  Exception{
    	Prueba pr = findById(prueba.getId());
    	if (pr==null) 
			throw new Exception("No existe");
		pr.setDescripcion(prueba.getDescripcion());
		em.merge(pr);
    }

}
