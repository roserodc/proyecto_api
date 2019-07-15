package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.Estado;
import proyecto_api.model.manager.ManagerEstados;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanEstados implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerEstados managerEstados;
	private List<Estado> lista;
	private Estado estado;
	private boolean panelColapsado;
	private Estado estadoSeleccionado;
	
	@PostConstruct
	public void inicializar() {
		lista=managerEstados.findAll();
		estado=new Estado();
		panelColapsado=true;
	}
	
	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}
	
	public void actionListenerInsertar() {
		try {
			managerEstados.insertar(estado);
			lista=managerEstados.findAll();
			estado=new Estado();
			JSFUtil.createMensajeInfo("Datos Insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerEstados.eliminar(id);
		lista=managerEstados.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Estado estado) {
		estadoSeleccionado = estado;
	}
	
	public void actionListenerActualizar() {
		try {
			managerEstados.actualizar(estadoSeleccionado);
			lista=managerEstados.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Estado> getLista() {
		return lista;
	}

	public void setLista(List<Estado> lista) {
		this.lista = lista;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstadoSeleccionado() {
		return estadoSeleccionado;
	}

	public void setEstadoSeleccionado(Estado estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	
}
