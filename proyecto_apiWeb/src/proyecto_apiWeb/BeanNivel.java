package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.Nivel;
import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Prueba;
import proyecto_api.model.manager.ManagerNivel;
import proyecto_api.model.manager.ManagerNivel;
import proyecto_api.model.manager.ManagerPrueba;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanNivel implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerNivel managerNivel;
	private List<Nivel> lista;
	private Nivel nivel;
	private boolean panelColapsado;
	private Nivel selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerNivel.findAll();
		nivel = new Nivel();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
			managerNivel.insertar(nivel);
			lista = managerNivel.findAll();
			nivel = new Nivel();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerNivel.eliminar(id);
		lista=managerNivel.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Nivel nivel) {
		selecionada = nivel;
	}
	
	public void actionListenerActualizar() {
		try {
			managerNivel.actualizar(selecionada);
			lista=managerNivel.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Nivel> getLista() {
		return lista;
	}

	public void setLista(List<Nivel> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Nivel getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Nivel selecionada) {
		this.selecionada = selecionada;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

}
