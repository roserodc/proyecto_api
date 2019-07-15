package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Carrera;
import proyecto_api.model.entities.Club;
import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Nivel;
import proyecto_api.model.entities.Role;
import proyecto_api.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {
	@PersistenceContext
	private EntityManager en;
	
	@EJB
	public ManagerCarrera managerCarrera;
	@EJB
	public ManagerRoles managerRol;
	@EJB
	public ManagerNivel managerNivel;
	@EJB
	public ManagerClub managerClub;
	public ManagerUsuario() {
    	
    }
    
    public Usuario iniciarSesion( Usuario us) {
    	Usuario usuario = null;
    	String consulta;
    	try {
    		consulta= "FROM Usuario u  WHERE u.userCi  = ?1 and u.userPass = ?2";
    		Query query = en.createQuery(consulta);
    		query.setParameter(1, us.getUserCi());
    		query.setParameter(2, us.getUserPass());
    		List<Usuario> lista = query.getResultList();
    		  if(!lista.isEmpty()) {
    			  usuario=lista.get(0);
    		  }
    		
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
    	return usuario;
    }
    
    public List<Usuario>findAll(){
    	String c = "SELECT c FROM Usuario c order by user_id";
    	Query q = en.createQuery(c,Usuario.class);
    	return q.getResultList();
    }
    
    public Usuario findById(int id) {
    	return en.find(Usuario.class, id);
    }
    
    public Usuario insertar(Usuario usuario,
    		Integer idNivel,
    		Integer idRol,Integer idCarrera,Integer idClub) {
    	Usuario us = new Usuario();
    	Role rol;
    	Carrera ca;
    	Club clu;
    	Nivel ni;
    	rol=managerRol.findById(idRol);
    	ca=managerCarrera.findById(idCarrera);
    	clu=managerClub.findById(idClub);
    	ni=managerNivel.findById(idNivel);
    	us.setUserCi(usuario.getUserCi());
    	us.setUserPass(usuario.getUserPass());
    	us.setUserApellido(usuario.getUserApellido());
    	us.setUserNombre(usuario.getUserNombre());
    	us.setUserTelefono(usuario.getUserTelefono());
    	us.setRole(rol);
    	us.setCarrera(ca);
    	us.setClub(clu);
    	us.setNivel(ni);
    	en.persist(us);
    	return us;
    }

    
    public void eliminar (Integer id) {
    	Usuario us = findById(id);
    	if (us!=null) {
    		en.remove(us);
		}
    }
    
    public void actualizar (Usuario usuario) throws  Exception{
    	Usuario us = findById(usuario.getUserId());
    	if (us==null) 
			throw new Exception("No existe");
    	us.setUserCi(usuario.getUserCi());
    	us.setUserPass(usuario.getUserPass());
    	us.setUserApellido(usuario.getUserApellido());
    	us.setUserNombre(usuario.getUserNombre());
    	us.setUserTelefono(usuario.getUserTelefono());
		en.merge(us);
    }
    
   

}

