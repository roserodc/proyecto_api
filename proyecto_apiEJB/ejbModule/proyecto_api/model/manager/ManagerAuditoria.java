package proyecto_api.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import proyecto_api.model.entities.Bitacora;
import proyecto_api.model.entities.Usuario;

/**
 * Clase que implementa la logica de manejo de
 * pistas de auditoria.
 * @author mrea
 *
 */
@Stateless
@LocalBean
public class ManagerAuditoria {
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private ManagerUsuario managerUsuario;
	
	public ManagerAuditoria() {
		
	}
	
	/**
	 * Almacena la informacion de un evento en la tabla de auditoria.
	 * @param codigoUsuario Codigo del usuario que genero el evento.
	 * @param clase Clase que genera el evento.
	 * @param metodo Nombre del metodo que genero el evento.
	 * @param descripcion Informacion detallada del evento.
	 * @throws Exception 
	 */
	
	 public List<Bitacora>findAll(){
	    	String c = "SELECT b FROM Bitacora b order by b_id";
	    	Query q = em.createQuery(c,Bitacora.class);
	    	return q.getResultList();
	    }
	
	public void crearEvento(String codigoUsuario,Class clase,String metodo,String descripcion) throws Exception{
		Bitacora evento=new Bitacora();
		//cambio para probar git
		
		if(codigoUsuario==null||codigoUsuario.length()==0)
			throw new Exception("Error auditoria: debe indicar el codigo del usuario.");
		if(metodo==null||metodo.length()==0)
			throw new Exception("Error auditoria: debe indicar el metodo que genera el evento.");

		Usuario usuario=managerUsuario.findByCedula(Integer.parseInt(codigoUsuario));
		if(usuario==null)
			throw new Exception("Error auditoria: no existe el usuario indicado.");
		
		evento.setUsuario(usuario);
		evento.setBNombreevento(clase.getSimpleName()+"/"+metodo);
		evento.setBDescripcion(descripcion);
		evento.setBFechaevento(""+new Date());
		evento.setBIpmaquina("localhost");
		
		em.persist(evento);
	}

}
