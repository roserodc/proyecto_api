package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import proyecto_api.model.entities.Club;
import proyecto_api.model.entities.Estado;
import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Peticione;
import proyecto_api.model.entities.Prueba;
import proyecto_api.model.entities.TipoPeticion;
import proyecto_api.model.entities.Usuario;
import proyecto_api.model.entities.GuiaEntrenamiento;
import proyecto_api.model.manager.ManagerClub;
import proyecto_api.model.manager.ManagerEstados;
import proyecto_api.model.manager.ManagerFacultad;
import proyecto_api.model.manager.ManagerGuiaEntrenamiento;
import proyecto_api.model.manager.ManagerPeticion;
import proyecto_api.model.manager.ManagerPrueba;
import proyecto_api.model.manager.ManagerTipoPeticion;
import proyecto_api.model.manager.ManagerUsuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanPeticion implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPeticion managerPeticion;
	@EJB
	private ManagerTipoPeticion managerTipoPeticion;
	
	@EJB
	private ManagerEstados managerEstado;
	
	@EJB
	private ManagerUsuario managerUsuario;
	@EJB
	private ManagerGuiaEntrenamiento managerGuiaEntrenamiento;
	private Integer idTipoPeticion;
	private Integer idEstado;


	private Integer idUsuario;
	private Integer idGuiaEntrenamiento;
	private List<Peticione> lista;
	private Peticione peticion;
	private boolean panelColapsado;
	private Peticione selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerPeticion.findAll();
		peticion = new Peticione();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
			managerPeticion.insertar2(peticion,
					 idTipoPeticion,  idEstado,
					 idUsuario,idGuiaEntrenamiento);
			lista = managerPeticion.findAll();
			peticion = new Peticione();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerPeticion.eliminar(id);
		lista=managerPeticion.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Peticione peticion) {
		selecionada = peticion;
	}
	
	public void actionListenerActualizar() {
		try {
			managerPeticion.actualizar(selecionada);
			lista=managerPeticion.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Peticione> getLista() {
		return lista;
	}

	public void setLista(List<Peticione> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Peticione getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Peticione selecionada) {
		this.selecionada = selecionada;
	}

	public Peticione getPeticion() {
		return peticion;
	}

	public void setPeticion(Peticione peticion) {
		this.peticion = peticion;
	}

	public Integer getIdTipoPeticion() {
		return idTipoPeticion;
	}

	public void setIdTipoPeticion(Integer idTipoPeticion) {
		this.idTipoPeticion = idTipoPeticion;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdGuiaEntrenamiento() {
		return idGuiaEntrenamiento;
	}

	public void setIdGuiaEntrenamiento(Integer idGuiaEntrenamiento) {
		this.idGuiaEntrenamiento = idGuiaEntrenamiento;
	}
	
	public List<SelectItem> getListaTipoPeticionSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<TipoPeticion> listadoClientes=managerTipoPeticion.findAll();
		
		for(TipoPeticion c:listadoClientes){
			SelectItem item=new SelectItem(c.getTpId(), 
					                   c.getTpDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	public List<SelectItem> getListaEstadoSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Estado> listadoClientes=managerEstado.findAll();
		
		for(Estado c:listadoClientes){
			SelectItem item=new SelectItem(c.getEstId(), 
					                   c.getEstDescripcion());
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
	
	public List<SelectItem> getListaGuiaEntrenamietnoSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<GuiaEntrenamiento> listadoClientes=managerGuiaEntrenamiento.findAll();
		
		for(GuiaEntrenamiento c:listadoClientes){
			SelectItem item=new SelectItem(c.getGeId(), 
					                   c.getGeDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
}
