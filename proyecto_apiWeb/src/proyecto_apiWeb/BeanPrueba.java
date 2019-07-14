package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.Prueba;
import proyecto_api.model.manager.ManagerPrueba;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanPrueba implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPrueba managerPrueba;
	private List<Prueba> lista;
	private Prueba prueba;
	private boolean panelColapsado;
	private Prueba selecionada;
	
	@PostConstruct
	public void inicalizar() {
		lista = managerPrueba.findAll();
		prueba = new Prueba();
		panelColapsado=true;
	}
	
	public void actionListenerColapsarPanerl() {
		panelColapsado=!panelColapsado;
	}
	
	public void actionListenerInsertar() {
		try {
			managerPrueba.insertar(prueba);
			lista=managerPrueba.findAll();
			prueba = new Prueba();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	
	public List<Prueba> getLista() {
		return lista;
	}
	public void setLista(List<Prueba> lista) {
		this.lista = lista;
	}
	public Prueba getPrueba() {
		return prueba;
	}
	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public Prueba getSelecionada() {
		return selecionada;
	}
	public void setSelecionada(Prueba selecionada) {
		this.selecionada = selecionada;
	}
	
}
