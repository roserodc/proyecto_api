package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.Club;
import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Prueba;
import proyecto_api.model.manager.ManagerClub;
import proyecto_api.model.manager.ManagerFacultad;
import proyecto_api.model.manager.ManagerPrueba;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanFacultad implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerFacultad managerFacultad;
	private List<Facultad> lista;
	private Facultad facultad;
	private boolean panelColapsado;
	private Facultad selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerFacultad.findAll();
		facultad = new Facultad();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
			managerFacultad.insertar(facultad);
			lista = managerFacultad.findAll();
			facultad = new Facultad();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerFacultad.eliminar(id);
		lista=managerFacultad.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Facultad facultad) {
		selecionada = facultad;
	}
	
	public void actionListenerActualizar() {
		try {
			managerFacultad.actualizar(selecionada);
			lista=managerFacultad.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Facultad> getLista() {
		return lista;
	}

	public void setLista(List<Facultad> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Facultad getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Facultad selecionada) {
		this.selecionada = selecionada;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

}
