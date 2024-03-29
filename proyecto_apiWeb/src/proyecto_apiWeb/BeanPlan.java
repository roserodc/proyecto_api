package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Carrera;
import proyecto_api.model.entities.Rutina;
import proyecto_api.model.manager.ManagerCarrera;
import proyecto_api.model.manager.ManagerFacultad;
import proyecto_api.model.manager.ManagerPlan;
import proyecto_api.model.manager.ManagerPrueba;
import proyecto_api.model.manager.ManagerRutina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanPlan implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPlan managerPlan;

	@EJB
	private ManagerRutina managerRutina;
	
	private Integer idPlan;
	private List<Plane> lista;
	private List<Rutina> listaRutina;
	private List<Rutina> listaRutinas;
	private Plane plane;
	private boolean panelColapsado;
	private Plane selecionada;
	
	@Inject
	private BeanPlan beanplan;

	@PostConstruct
	public void inicalizar() {
		lista = managerPlan.findAll();
		plane = new Plane();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
//			managerCarrera.insertar(carrera);
			managerPlan.insertar(plane, idPlan);
			lista = managerPlan.findAll();
			plane = new Plane();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}

	public void actionListenerEliminar(Integer id) {
		try {
			managerPlan.eliminar(id);
			lista = managerPlan.findAll();
			JSFUtil.createMensajeInfo("Eliminado");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}

	public void actionListenerSeleccionado(Plane plan) {
		
		selecionada = plan;
		listaRutina = managerRutina.findAllDif();
		listaRutinas = managerRutina.findAllxPlan(selecionada.getPlId());
	}

	public void actionListenerActualizar() {
		try {
			managerPlan.actualizar(selecionada);
			lista = managerPlan.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Plane> getLista() {
		return lista;
	}

	public void setLista(List<Plane> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Plane getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Plane selecionada) {
		this.selecionada = selecionada;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public List<Rutina> getListaRutinas() {
		return listaRutinas;
	}

	public void setListaRutinas(List<Rutina> listaRutinas) {
		this.listaRutinas = listaRutinas;
	}

	public List<Rutina> getListaRutina() {
		return listaRutina;
	}

	public void setListaRutina(List<Rutina> listaRutina) {
		this.listaRutina = listaRutina;
	}
	

}
