package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Carrera;
import proyecto_api.model.entities.Facultad;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerCarrera {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
	@EJB
	public ManagerFacultad managerFacultad;
	@EJB
	public ManagerCarrera managerCarrera;
    public ManagerCarrera() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Carrera>findAll(){
    	String c = "SELECT c FROM Carrera c order by ca_id";
    	Query q = em.createQuery(c,Carrera.class);
    	return q.getResultList();
    }
    
    public Carrera findById(int id) {
    	return em.find(Carrera.class, id);
    }
    
    public Carrera insertar(Carrera carrera) {
    	Carrera ca = new Carrera();
    	ca.setCaDescripcion(carrera.getCaDescripcion());
    	ca.setCaAcronimo(carrera.getCaAcronimo());
    	ca.setFacultad(carrera.getFacultad());
    	em.persist(ca);
    	return ca;
    }
    
    public Carrera insertar2 (Carrera carrera,Integer idfacultad) {
    	Facultad fa;
    	fa=managerFacultad.findById(idfacultad);
    	Carrera ca = new Carrera();
    	ca.setCaDescripcion(carrera.getCaDescripcion());
    	ca.setCaAcronimo(carrera.getCaAcronimo());;
    	ca.setFacultad(fa);
    	em.persist(ca);
    	return ca;
    }
    
    public void eliminar (Integer id) {
    	Carrera ca = findById(id);
    	if (ca!=null) {
    		em.remove(ca);
		}
    }
    
    public void actualizar (Carrera carrera) throws  Exception{
    	Carrera ca = findById(carrera.getCaId());
    	if (ca==null) 
			throw new Exception("No existe");
    	ca.setCaDescripcion(carrera.getCaDescripcion());
    	ca.setCaAcronimo(carrera.getCaAcronimo());
    	ca.setFacultad(carrera.getFacultad());
		em.merge(ca);
    }

}
