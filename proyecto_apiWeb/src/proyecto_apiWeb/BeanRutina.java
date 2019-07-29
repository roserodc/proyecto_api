package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Rutina;
import proyecto_api.model.entities.Carrera;
import proyecto_api.model.manager.ManagerCarrera;
import proyecto_api.model.manager.ManagerFacultad;
import proyecto_api.model.manager.ManagerPlan;
import proyecto_api.model.manager.ManagerPrueba;
import proyecto_api.model.manager.ManagerRutina;
import proyecto_apiWeb.BeanPlan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanRutina implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerRutina managerRutina;
	@EJB
	private ManagerPlan managerPlan;
	private Integer idPlan;

	private List<Rutina> lista;
	private List<Rutina> listaDiferente;
	private Rutina rutina;
	private boolean panelColapsado;
	private Rutina selecionada;

	@Inject
	private BeanPlan beanplan;
	
	@PostConstruct
	public void inicalizar() {
		lista = managerRutina.findAll();
		
//		if (!beanplan.getSelecionada().getPlId().equals(null)) {
//			listaDiferente = managerRutina.findAllDif(beanplan.getSelecionada().getPlId());
//		}
		
		
		rutina = new Rutina();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {

			if (managerRutina.comprobar(rutina, idPlan)) {
				JSFUtil.createMensajeError("Ya Existe");
			} else {
				System.out.println("bean " + idPlan);

				managerRutina.insertar(rutina, idPlan);

				System.out.println("bean " + idPlan);
				lista = managerRutina.findAll();
				rutina = new Rutina();
				JSFUtil.createMensajeInfo("insertados");
			}

		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	

	public void actionListenerInsertarxPlan(Integer idPlan) {
		try {

			if (managerRutina.comprobar(rutina, idPlan)) {
				JSFUtil.createMensajeError("Ya Existe");
			} else {
				System.out.println("bean " + idPlan);

				managerRutina.insertar(rutina, idPlan);

				System.out.println("bean " + idPlan);
				lista = managerRutina.findAll();
				rutina = new Rutina();
				JSFUtil.createMensajeInfo("insertados");
			}

		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	

//	public void actionListenerAddRutinaxPlan(Rutina rutina, Integer idPlan) {
//		try {
//			
//			if (!rutina.getPlane2().equals(null)) {
//				
//			}else {
//				
//			}
//			
//			managerRutina.actualizarRutinaxPlan(rutina , idPlan);
//			lista = managerRutina.findAll();
//			JSFUtil.createMensajeInfo("Acualizado");
//		} catch (Exception e) {
//			// TODO: handle exception
//			JSFUtil.createMensajeError(e.getMessage());
//			e.printStackTrace();
//		}
//	}
	
	public void actionListenerEliminar(Integer id) {
		managerRutina.eliminar(id);
		lista = managerRutina.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}

	public void actionListenerSeleccionado(Rutina rutina) {
		selecionada = rutina;
	}

	public void actionListenerActualizar() {
		try {
			managerRutina.actualizar(selecionada);
			lista = managerRutina.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Rutina> getLista() {
		return lista;
	}

	public void setLista(List<Rutina> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Rutina getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Rutina selecionada) {
		this.selecionada = selecionada;
	}

	public Rutina getRutina() {
		return rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	public List<SelectItem> getListaSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Plane> listadoClientes = managerPlan.findAll();

		for (Plane c : listadoClientes) {
			SelectItem item = new SelectItem(c.getPlId(), c.getPlTipo());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public void setIdFacultad(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public List<Rutina> getListaDiferente() {
		return listaDiferente;
	}

	public void setListaDiferente(List<Rutina> listaDiferente) {
		this.listaDiferente = listaDiferente;
	}
	
}
