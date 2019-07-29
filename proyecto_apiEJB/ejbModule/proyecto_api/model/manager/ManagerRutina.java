package proyecto_api.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Rutina;
import proyecto_api.model.entities.Usuario;

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

	public List<Rutina> findAll() {
		String c = "SELECT c FROM Rutina c order by rt_id";
		Query q = em.createQuery(c, Rutina.class);
		return q.getResultList();
	}

	public List<Rutina> findAllxPlan(Integer idPlan) {
		String c = "SELECT c FROM Rutina c WHERE pl_id_planes = " + idPlan + "order by rt_id";
		Query q = em.createQuery(c, Rutina.class);
		return q.getResultList();
	}

	public List<Rutina> findAllDif() {
		String c = "SELECT c FROM Rutina c WHERE pl_id_planes = null order by rt_id";
		Query q = em.createQuery(c, Rutina.class);
		return q.getResultList();
	}

	public Rutina findById(int id) {
		return em.find(Rutina.class, id);
	}

	public boolean comprobar(Rutina rutina, Integer idPlan) {
		Plane pl;
		pl = managerPlan.findById(idPlan);

		System.out.println("entra al comprobar");
		boolean var = false;
		List<Rutina> ListaRutina = findAllxPlan(idPlan);

		for (Rutina r : ListaRutina) {
			System.out.println("entra al for");

			if ((r.getRtDescripcion().equals(rutina.getRtDescripcion()))
					&& (r.getRtDuracion().equals(rutina.getRtDuracion()))
					&& (r.getRtRepeticiones().equals(rutina.getRtRepeticiones()))
					&& (r.getRtSeries().equals(rutina.getRtSeries()))
					&& (r.getPlane2().getPlId().equals(pl.getPlId()))) {
				System.out.println("entra al if");
				var = true;

				break;
			}
		}
		System.out.println("var " + var);
		return var;
	}

	public boolean comprobarRutina(Rutina rutina, Integer idPlan) {
		Plane pl;
		pl = managerPlan.findById(idPlan);

		System.out.println("entra al comprobar");
		boolean var = false;
		List<Rutina> ListaRutina = findAllxPlan(idPlan);

		for (Rutina r : ListaRutina) {
			System.out.println("entra al for");

			if ((r.getRtDescripcion().equals(rutina.getRtDescripcion()))
					&& (r.getRtDuracion().equals(rutina.getRtDuracion()))
					&& (r.getRtRepeticiones().equals(rutina.getRtRepeticiones()))
					&& (r.getRtSeries().equals(rutina.getRtSeries()))) {
				System.out.println("entra al if");
				var = true;

				break;
			}
		}
		System.out.println("var " + var);
		return var;
	}

	public boolean comprobarRutinaNull(Rutina rutina) {
	

		System.out.println("entra al comprobar");
		boolean var = false;
		List<Rutina> ListaRutina = findAllDif();

		for (Rutina r : ListaRutina) {
			System.out.println("entra al for");

			if ((r.getRtDescripcion().equals(rutina.getRtDescripcion()))
					&& (r.getRtDuracion().equals(rutina.getRtDuracion()))
					&& (r.getRtRepeticiones().equals(rutina.getRtRepeticiones()))
					&& (r.getRtSeries().equals(rutina.getRtSeries()))) {
				System.out.println("entra al if");
				var = true;

				break;
			}
		}
		System.out.println("var " + var);
		return var;
	}
	
	
//	public List<Rutina> MostrarLista(Integer idPlan) {
//		List<Rutina> lista = null;
//		System.out.println("entra al comprobar");
//		int aux = 0;
//		List<Rutina> PorPlan = findAllxPlan(idPlan);
//		List<Rutina> Todas = findAll();
//		
//		for (Rutina r : Todas) {
//			System.out.println("entra al for");
//			for (Rutina t : PorPlan) {
//				System.out.println("entra al for");
//
//				if ((r.getRtDescripcion().equals(t.getRtDescripcion()))
//						&& (r.getRtDuracion().equals(t.getRtDuracion()))
//						&& (r.getRtRepeticiones().equals(t.getRtRepeticiones()))
//						&& (r.getRtSeries().equals(t.getRtSeries()))) {
//					
//					System.out.println("Igual "+ t.getRtId());
//					System.out.println("Igual "+ r.getRtId());
//					System.out.println("Igual "+ t.getRtDescripcion());
//					System.out.println("Igual "+ r.getRtDescripcion());
//				}else {
//					
//					System.out.println("Diferente "+ t.getRtId());
//					System.out.println("Diferente "+ r.getRtId());
//					System.out.println("Diferente "+ t.getRtDescripcion());
//					System.out.println("Diferente "+ r.getRtDescripcion());
//					lista.set(aux, r);
//					
//					aux++;
//				}
//			}
//			
//		}
//
//		return lista;
//	}

	public Rutina insertarNull(Rutina rutina) {
		
		Rutina rt = new Rutina();
		rt.setRtDescripcion(rutina.getRtDescripcion());
		rt.setRtDuracion(rutina.getRtDuracion());
		rt.setRtRepeticiones(rutina.getRtRepeticiones());
		rt.setRtSeries(rutina.getRtSeries());
		em.persist(rt);
		return rt;
	}
	
	
	public Rutina insertar(Rutina rutina, Integer idPlan) {
		System.out.println("manager" + idPlan);

		Plane pl;
		pl = managerPlan.findById(idPlan);
		Rutina rt = new Rutina();
		rt.setRtDescripcion(rutina.getRtDescripcion());
		rt.setRtDuracion(rutina.getRtDuracion());
		rt.setRtRepeticiones(rutina.getRtRepeticiones());
		rt.setRtSeries(rutina.getRtSeries());
		rt.setPlane2(pl);
		em.persist(rt);
		return rt;
	}

	public Rutina insertarRutinaxPlan(Rutina rutina, Integer idPlan) {
		System.out.println("manager" + idPlan);

		Plane pl;
		pl = managerPlan.findById(idPlan);
		Rutina rt = new Rutina();
		rt.setRtDescripcion(rutina.getRtDescripcion());
		rt.setRtDuracion(rutina.getRtDuracion());
		rt.setRtRepeticiones(rutina.getRtRepeticiones());
		rt.setRtSeries(rutina.getRtSeries());
		rt.setPlane2(pl);
		em.persist(rt);
		return rt;
	}

	public void eliminar(Integer id) {
		Rutina rt = findById(id);
		if (rt != null) {
			em.remove(rt);
		}
	}

	public void actualizar(Rutina rutina) throws Exception {
		Rutina rt = findById(rutina.getRtId());
		if (rt == null)
			throw new Exception("No existe");
		rt.setRtDescripcion(rutina.getRtDescripcion());
		rt.setRtDuracion(rutina.getRtDuracion());
		rt.setRtRepeticiones(rutina.getRtRepeticiones());
		rt.setRtSeries(rutina.getRtSeries());
		rt.setPlane1(rutina.getPlane1());
		em.merge(rt);
	}

}
