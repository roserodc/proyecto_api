package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.GuiaEntrenamiento;
import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Prueba;
import proyecto_api.model.manager.ManagerClub;
import proyecto_api.model.manager.ManagerGuiaEntrenamiento;
import proyecto_api.model.manager.ManagerPrueba;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanGuiaEntrenamiento implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerGuiaEntrenamiento managerGuiaEntrenamiento;
	private List<GuiaEntrenamiento> lista;
	private GuiaEntrenamiento guiaEntrenamiento;
	private boolean panelColapsado;
	private GuiaEntrenamiento selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerGuiaEntrenamiento.findAll();
		guiaEntrenamiento = new GuiaEntrenamiento();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
			managerGuiaEntrenamiento.insertar(guiaEntrenamiento);
			lista = managerGuiaEntrenamiento.findAll();
			guiaEntrenamiento = new GuiaEntrenamiento();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerGuiaEntrenamiento.eliminar(id);
		lista=managerGuiaEntrenamiento.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(GuiaEntrenamiento guiaEntrenamiento) {
		selecionada = guiaEntrenamiento;
	}
	
	public void actionListenerActualizar() {
		try {
			managerGuiaEntrenamiento.actualizar(selecionada);
			lista=managerGuiaEntrenamiento.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<GuiaEntrenamiento> getLista() {
		return lista;
	}

	public void setLista(List<GuiaEntrenamiento> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public GuiaEntrenamiento getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(GuiaEntrenamiento selecionada) {
		this.selecionada = selecionada;
	}

	public GuiaEntrenamiento getGuiaEntrenamiento() {
		return guiaEntrenamiento;
	}

	public void setGuiaEntrenamiento(GuiaEntrenamiento guiaEntrenamiento) {
		this.guiaEntrenamiento = guiaEntrenamiento;
	}

}
