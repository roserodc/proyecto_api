package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Facultad;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerFacultad {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerFacultad() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Facultad>findAll(){
    	String c = "SELECT f FROM Facultad f order by f_id";
    	Query q = em.createQuery(c,Facultad.class);
    	return q.getResultList();
    }
    
    public Facultad findById(int id) {
    	return em.find(Facultad.class, id);
    }
    
    public Facultad insertar(Facultad facultad) {
    	Facultad facu = new Facultad();
    	facu.setFDescripcion(facultad.getFDescripcion());
    	facu.setFAcronimo(facultad.getFAcronimo());
    	em.persist(facu);
    	return facu;
    }
    
    public void eliminar (Integer id) {
    	Facultad facu = findById(id);
    	if (facu!=null) {
    		em.remove(facu);
		}
    }
    
    public void actualizar (Facultad facultad) throws  Exception{
    	Facultad facu = findById(facultad.getFId());
    	if (facu==null) 
			throw new Exception("No existe");
    	facu.setFDescripcion(facultad.getFDescripcion());
    	facu.setFAcronimo(facultad.getFAcronimo());
		em.merge(facu);
    }

}
