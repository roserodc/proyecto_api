package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;


import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.GuiaEntrenamiento;
import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Carrera;
import proyecto_api.model.manager.ManagerCarrera;
import proyecto_api.model.manager.ManagerFacultad;
import proyecto_api.model.manager.ManagerGuiaEntrenamiento;
import proyecto_api.model.manager.ManagerPlan;
import proyecto_api.model.manager.ManagerPrueba;

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
	private ManagerGuiaEntrenamiento managerGuiaEntrenamiento;
	private Integer idPlan;
	private List<Plane> lista;
	private Plane plane;
	private boolean panelColapsado;
	private Plane selecionada;

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
		managerPlan.eliminar(id);
		lista=managerPlan.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Plane plan) {
		selecionada = plan;
	}
	
	public void actionListenerActualizar() {
		try {
			managerPlan.actualizar(selecionada);
			lista=managerPlan.findAll();
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

	public List<SelectItem> getListaGuiaEntrenamientoSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<GuiaEntrenamiento> listadoClientes=managerGuiaEntrenamiento.findAll();
		
		for(GuiaEntrenamiento c:listadoClientes){
			SelectItem item=new SelectItem(c.getGeId(), 
					                   c.getGeDescripcion());
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

	
}
