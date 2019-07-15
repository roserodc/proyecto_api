package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.TipoPeticion;

/**
 * Session Bean implementation class ManagerTIpoPeticion
 */
@Stateless
@LocalBean
public class ManagerTipoPeticion {
	@PersistenceContext
	private EntityManager em;
	
    public ManagerTipoPeticion() {
        // TODO Auto-generated constructor stub
    }
    
    public List<TipoPeticion> findAll(){
    	String consulta="select o from TipoPeticion o order by o.tpId";
    	Query q=em.createQuery(consulta, TipoPeticion.class);
    	return q.getResultList();
    }
    
    public TipoPeticion findById(int id) {
    	return em.find(TipoPeticion.class, id);
    }
    
    public TipoPeticion insertar(TipoPeticion tipoPe) {
    	TipoPeticion tipop=new TipoPeticion();
    	tipop.setTpDescripcion(tipoPe.getTpDescripcion());
    	em.persist(tipop);
    	return tipop;    	
    }
    
    public void eliminar (Integer id) {
    	TipoPeticion tipop=findById(id);
    	if(tipop!=null) {
    		em.remove(tipop);
    	}
    }
    
    public void actualizar (TipoPeticion tipoPe) throws Exception{
    	TipoPeticion tipop=findById(tipoPe.getTpId());
    	if(tipop==null)
    		throw new Exception("No existe");
    	tipop.setTpDescripcion(tipoPe.getTpDescripcion());
    	em.merge(tipop);
    }
    
}
