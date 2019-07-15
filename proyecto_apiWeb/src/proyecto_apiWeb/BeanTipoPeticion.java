package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.TipoPeticion;
import proyecto_api.model.manager.ManagerTipoPeticion;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanTipoPeticion implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerTipoPeticion managertipoPeticion;
	private List<TipoPeticion> lista;
	private TipoPeticion tipop;
	private boolean panelColapsado;
	private TipoPeticion tipopseleccionada;
	
	@PostConstruct
	public void inicializar() {
		lista=managertipoPeticion.findAll();
		tipop=new TipoPeticion();
		panelColapsado=true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	
	public void actionListenerInsertar() {
		try {
			managertipoPeticion.insertar(tipop);
			lista=managertipoPeticion.findAll();
			tipop=new TipoPeticion();
			JSFUtil.createMensajeInfo("Datos Insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managertipoPeticion.eliminar(id);
		lista=managertipoPeticion.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(TipoPeticion tipope) {
		tipopseleccionada=tipope;
	}
	
	public void actionListenerActualizar() {
		try {
			managertipoPeticion.actualizar(tipopseleccionada);
			lista=managertipoPeticion.findAll();
			JSFUtil.createMensajeInfo("Actualizado");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<TipoPeticion> getLista() {
		return lista;
	}

	public void setLista(List<TipoPeticion> lista) {
		this.lista = lista;
	}

	public TipoPeticion getTipop() {
		return tipop;
	}

	public void setTipop(TipoPeticion tipop) {
		this.tipop = tipop;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public TipoPeticion getTipopseleccionada() {
		return tipopseleccionada;
	}

	public void setTipopseleccionada(TipoPeticion tipopseleccionada) {
		this.tipopseleccionada = tipopseleccionada;
	}

	
}
