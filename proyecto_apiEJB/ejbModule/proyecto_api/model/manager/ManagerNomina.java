package proyecto_api.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Usuario;
import proyecto_api.model.entities.Nomina;
import proyecto_api.model.entities.Peticione;
import proyecto_api.model.entities.Plane;

/**
 * Session Bean implementation class ManagerNomina
 */
@Stateless
@LocalBean
public class ManagerNomina {

	/**
	 * Default constructor.
	 */
	public ManagerNomina() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager em;

	@EJB
	public ManagerUsuario managerUsuario;
	@EJB
	public ManagerPeticion managerPeticion;

	
	 public List<Nomina>findAll(){
	    	String n = "SELECT n FROM Nomina n order by nm_id";
	    	Query q = em.createQuery(n,Nomina.class);
	    	return q.getResultList();
	    }
	    
	
	public Nomina insertar(Nomina nomina, Integer idpeticion, Integer idusuario) {
		Peticione ptc;
		ptc = managerPeticion.findById(idpeticion);
		Usuario usr;
		usr = managerUsuario.findById(idusuario);
		Nomina nom = new Nomina();
		nom.setPeticione(ptc);
		nom.setUsuario(usr);
//    	pl.setRutina(rut);
		em.persist(nom);
		return nom;
	}

	public Nomina findById(int id) {
		return em.find(Nomina.class, id);
	}

	public void eliminar(Integer id) {
		Nomina nom = findById(id);
		if (nom != null) {
			em.remove(nom);
		}
	}

	public void actualizar(Nomina nomina, Integer idpeticion, Integer idusuario) throws Exception {
		Nomina nom = findById(nomina.getNmId());
		if (nom == null)
			throw new Exception("No existe");
		Peticione ptc;
		ptc = managerPeticion.findById(idpeticion);
		Usuario usr;
		usr = managerUsuario.findById(idusuario);
		nom.setPeticione(ptc);
		nom.setUsuario(usr);
		em.merge(nom);
	}

}