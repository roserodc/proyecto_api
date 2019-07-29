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
import proyecto_api.model.entities.Estado;
import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Peticione;
import proyecto_api.model.entities.TipoPeticion;
import proyecto_api.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerClub
 */
@Stateless
@LocalBean
public class ManagerPeticion {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
	@EJB
	public ManagerPeticion managerPeticion;
	@EJB
	public ManagerUsuario managerUsuario;
	@EJB
	public ManagerTipoPeticion managerTipoPeticion;
	@EJB
	public ManagerEstados managerEstado;
	@EJB
	public ManagerPlan managerPlan;

	
    public ManagerPeticion() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Peticione>findAll(){
    	String c = "SELECT p FROM Peticione p order by ptc_Id";
    	Query q = em.createQuery(c,Peticione.class);
    	return q.getResultList();
    }
    
    
    
    public List<Peticione>findAll2 (Integer id){
    	String c =  "SELECT o FROM Peticione o WHERE user_id_usuario=" + id +" order by ptc_Id";
    	Query q = em.createQuery(c,Peticione.class);
    	return q.getResultList();
    }
   
    public List<Peticione>findAllIndividuales (){
    	String c =  "SELECT o FROM Peticione o WHERE tp_id_tipo_peticion=1 order by ptc_Id";
    	Query q = em.createQuery(c,Peticione.class);
    	return q.getResultList();
    }
    
    public List<Peticione>findAllGrupales (){
    	String c =  "SELECT o FROM Peticione o WHERE tp_id_tipo_peticion=2 order by ptc_Id";
    	Query q = em.createQuery(c,Peticione.class);
    	return q.getResultList();
    }
    
    public Peticione findById(int id) {
    	return em.find(Peticione.class, id);
    }
    
    public Peticione insertar(Peticione peticion) {
    	Peticione pte = new Peticione();
    	pte.setPtcFecha(peticion.getPtcFecha());
    	pte.setPtcHoraInicio(peticion.getPtcHoraInicio());
    	pte.setPtcHoraFin(peticion.getPtcHoraFin());
    	pte.setTipoPeticion(peticion.getTipoPeticion());
    	pte.setEstado(peticion.getEstado());
    	pte.setUsuario(peticion.getUsuario());
    	pte.setPlan(peticion.getPlan());
    	em.persist(pte);
    	return pte;
    }
    
    public Peticione insertar2(Peticione peticion, Integer idTipoP,
    		 Integer idEstado, Integer idUsuario, Integer idplan) {
    	TipoPeticion tp;
    	Estado est;
    	Usuario us;
    	Plane pl;
    	String r="";
    	tp=managerTipoPeticion.findById(idTipoP);
    	est=managerEstado.findById(idEstado);
    	us=managerUsuario.findById(idUsuario);
    	pl=managerPlan.findById(idplan);
    	Peticione pte = new Peticione();
    	r = peticion.getPtcFecha().replace("19:00:00 COT 19", ".");
		System.out.println("-----fecha---*" + r);
    	pte.setPtcFecha(r);
    	pte.setPtcHoraInicio(peticion.getPtcHoraInicio());
    	pte.setPtcHoraFin(peticion.getPtcHoraFin());
    	pte.setTipoPeticion(tp);
    	pte.setEstado(est);
    	pte.setUsuario(us);
    	pte.setPlan(pl);
    	em.persist(pte);
    	return pte;
    }
    
    
    
    public void eliminar (Integer id) {
    	Peticione pte = findById(id);
    	if (pte!=null) {
    		em.remove(pte);
		}
    }
    
    public void actualizar (Peticione peticion) throws  Exception{
    	Peticione pte = findById((int) peticion.getPtcId());
    	if (pte==null) 
			throw new Exception("No existe");
    	pte.setPtcFecha(peticion.getPtcFecha());
    	pte.setPtcHoraInicio(peticion.getPtcHoraInicio());
    	pte.setPtcHoraFin(peticion.getPtcHoraFin());
    	pte.setTipoPeticion(peticion.getTipoPeticion());
    	pte.setEstado(peticion.getEstado());
    	pte.setUsuario(peticion.getUsuario());
    	pte.setPlan(peticion.getPlan());
		em.merge(pte);
    }
    
    public void actualizarAceptar(Integer ptcId ) throws  Exception{
    	Peticione pte = findById(ptcId);
    	if (pte==null) 
			throw new Exception("No existe");
    	Estado est;
    	est=managerEstado.findById(2);
      	pte.setEstado(est);
    	
		em.merge(pte);
    }

    public void actualizarRechazar(Integer ptcId ) throws  Exception{
    	Peticione pte = findById(ptcId);
    	if (pte==null) 
			throw new Exception("No existe");
    	Estado est;
    	est=managerEstado.findById(3);
      	pte.setEstado(est);
    	
		em.merge(pte);
    }

}
