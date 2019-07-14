package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {
	@PersistenceContext
	private EntityManager en;
	
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
    
    public List<Usuario> findAllUsuarios(){
    	String consulta ="select u from Usuario u order by user_id";
    	Query q= en.createQuery(consulta,Usuario.class);
    	return q.getResultList();
    }
    
    public Usuario findUsarioById(int id_usuario) {
    	return en.find(Usuario.class, id_usuario);
    }
    
    public void insertarUsuario(Usuario usuario) throws Exception{
    	if(findUsarioById(usuario.getUserId())!= null)
    		throw new Exception("Ya existe el usuario indicado");
    	en.persist(usuario);
    }
    
    public void eliminarUsuario(int id_usuario) {
    	Usuario usuario =findUsarioById(id_usuario);
    	if	(usuario!=null)
    		en.remove(usuario);
    }
    
    public void actualizarUsuario(Usuario usuario) throws Exception {
    	Usuario user = findUsarioById(usuario.getUserId());
    	if(user==null)
    		throw new Exception("No existe el usuario con el Id especificada");
    	user.setUserCi(usuario.getUserCi());
    	user.setUserApellido(usuario.getUserApellido());
    	user.setUserNombre(usuario.getUserNombre());
    	user.setUserTelefono(usuario.getUserTelefono());
    	user.setUserPass(usuario.getUserPass());
//    	user.setCaIdCarrera(usuario.getCaIdCarrera());
//    	user.setCluIdClub(usuario.getCluIdClub());
    	en.merge(user);
    }
    
   

}

