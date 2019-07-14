package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Club;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerClub {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerClub() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Club>findAll(){
    	String c = "SELECT c FROM Club c";
    	Query q = em.createQuery(c,Club.class);
    	return q.getResultList();
    }
    
    public Club findById(int id) {
    	return em.find(Club.class, id);
    }
    
    public Club insertar(Club club) {
    	Club clu = new Club();
    	clu.setCluDescripcion(club.getCluDescripcion());
    	em.persist(clu);
    	return clu;
    }
    
    public void eliminar (Integer id) {
    	Club clu = findById(id);
    	if (clu!=null) {
    		em.remove(clu);
		}
    }
    
    public void actualizar (Club club) throws  Exception{
    	Club clu = findById(club.getCluId());
    	if (clu==null) 
			throw new Exception("No existe");
    	clu.setCluDescripcion(club.getCluDescripcion());
		em.merge(clu);
    }

}
