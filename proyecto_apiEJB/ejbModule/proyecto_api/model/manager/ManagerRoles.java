package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Role;

/**
 * Session Bean implementation class ManagerRole
 */
@Stateless
@LocalBean
public class ManagerRoles {
	@PersistenceContext
	private EntityManager em;
	
    public ManagerRoles() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Role>findAll(){
    	String r="SELECT r FROM Role r order by rId";
    	Query q=em.createQuery(r, Role.class);
    	return q.getResultList();
    }
    
    public Role findById(int id) {
    	return em.find(Role.class, id);
    }
    
    public Role insertar(Role roles) {
    	Role rol=new Role();
    	rol.setRDescripcion(roles.getRDescripcion());
    	em.persist(rol);
    	return rol;
    }
    
    public void eliminar(Integer id) {
    	Role rol=findById(id);
    	if(rol!=null) {
    		em.remove(rol);
    	}
    }
    
    public void actualizar(Role roles) throws Exception{
    	Role rol=findById(roles.getRId());
    	if(rol==null)
    		throw new Exception("No existe");
    	rol.setRDescripcion(roles.getRDescripcion());
    	em.merge(rol);
    }

}
