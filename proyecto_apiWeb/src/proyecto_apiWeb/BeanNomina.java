package proyecto_apiWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import proyecto_api.model.entities.Usuario;
import proyecto_api.model.manager.ManagerFacultad;
import proyecto_api.model.manager.ManagerPeticion;
import proyecto_api.model.manager.ManagerUsuario;
import proyecto_api.model.manager.ManagerNomina;
import proyecto_api.model.entities.Carrera;
import proyecto_api.model.entities.Peticione;
import proyecto_api.model.entities.Nomina;
@Named
@SessionScoped

public class BeanNomina implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerNomina managerNomina;
	@EJB
	private ManagerPeticion managerPeticion;
	@EJB
	private ManagerUsuario managerUsuario;
	
	
	private Integer idPeticion;
	private Integer idUsuario;
	private List<Nomina> lista;
	private Nomina nomina;
	
	private boolean panelColapsado;
	private Nomina selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerNomina.findAll();
		nomina = new Nomina();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
//			managerCarrera.insertar(carrera);
			managerNomina.insertar(nomina, idPeticion,idUsuario);
			lista = managerNomina.findAll();
			nomina = new Nomina();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerNomina.eliminar(id);
		lista=managerNomina.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Nomina nomina) {
		selecionada = nomina;
	}
	
	public void actionListenerActualizar() {
		try {
			managerNomina.actualizar(selecionada, idPeticion,idUsuario);
			lista=managerNomina.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

		
	public ManagerNomina getManagerNomina() {
		return managerNomina;
	}

	public void setManagerNomina(ManagerNomina managerNomina) {
		this.managerNomina = managerNomina;
	}

	public Integer getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(Integer idPeticion) {
		this.idPeticion = idPeticion;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Nomina> getLista() {
		return lista;
	}

	public void setLista(List<Nomina> lista) {
		this.lista = lista;
	}

	public Nomina getNomina() {
		return nomina;
	}

	public void setNomina(Nomina nomina) {
		this.nomina = nomina;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Nomina getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Nomina selecionada) {
		this.selecionada = selecionada;
	}

	public List<SelectItem> getListaPeticionSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Peticione> listadoPeticion=managerPeticion.findAll();
		
		for(Peticione p:listadoPeticion){
			SelectItem item=new SelectItem(p.getPtcId());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	public List<SelectItem> getListaUsuarioSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Usuario> listadoClientes=managerUsuario.findAll();
		
		for(Usuario c:listadoClientes){
			SelectItem item=new SelectItem(c.getUserId(), 
					                   c.getUserNombre()+" "+c.getUserApellido());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
}
