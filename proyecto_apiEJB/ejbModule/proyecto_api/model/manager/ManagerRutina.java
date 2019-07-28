package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Rutina;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerRutina {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
	@EJB
	public ManagerPlan managerPlan;
    public ManagerRutina() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Rutina>findAll(){
    	String c = "SELECT c FROM Rutina c order by rt_id";
    	Query q = em.createQuery(c,Rutina.class);
    	return q.getResultList();
    }
    
    public Rutina findById(int id) {
    	return em.find(Rutina.class, id);
    }
    
    public Rutina insertar(Rutina rutina, Integer idPlan) {
    	Plane pla;
    	pla=managerPlan.findById(idPlan);
    	Rutina rt = new Rutina();
    	rt.setRtDescripcion(rutina.getRtDescripcion());
    	rt.setRtDuracion(rutina.getRtDuracion());
    	rt.setRtRepeticiones(rutina.getRtRepeticiones());
    	rt.setRtSeries(rutina.getRtSeries());
    	rt.setPlane1(pla);
    	em.persist(rt);
    	return rt;
    }
    
    public void eliminar (Integer id) {
    	Rutina rt = findById(id);
    	if (rt!=null) {
    		em.remove(rt);
		}
    }
    
    public void actualizar (Rutina rutina) throws  Exception{
    	Rutina rt = findById(rutina.getRtId());
    	if (rt==null) 
			throw new Exception("No existe");
    	rt.setRtDescripcion(rutina.getRtDescripcion());
    	rt.setRtDuracion(rutina.getRtDuracion());
    	rt.setRtRepeticiones(rutina.getRtRepeticiones());
    	rt.setRtSeries(rutina.getRtSeries());
    	rt.setPlane1(rutina.getPlane1());
		em.merge(rt);
    }

}
