package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.Club;
import proyecto_api.model.entities.Prueba;
import proyecto_api.model.manager.ManagerClub;
import proyecto_api.model.manager.ManagerPrueba;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanClub implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerClub managerClub;
	private List<Club> lista;
	private Club club;
	private boolean panelColapsado;
	private Club selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerClub.findAll();
		club = new Club();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
			managerClub.insertar(club);
			lista = managerClub.findAll();
			club = new Club();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}

	public List<Club> getLista() {
		return lista;
	}

	public void setLista(List<Club> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Club getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Club selecionada) {
		this.selecionada = selecionada;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

}
