package proyecto_apiWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
//import javax.management.relation.Role;

import proyecto_api.model.entities.Carrera;
import proyecto_api.model.entities.Club;
import proyecto_api.model.entities.Nivel;
import proyecto_api.model.entities.Usuario;
import proyecto_api.model.entities.Role;
import proyecto_api.model.manager.ManagerCarrera;
import proyecto_api.model.manager.ManagerClub;
import proyecto_api.model.manager.ManagerNivel;
import proyecto_api.model.manager.ManagerRoles;
import proyecto_api.model.manager.ManagerUsuario;

@Named
@SessionScoped
public class BeanUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUsuario managerUsuario;
	@EJB
	private ManagerNivel managerNivel;
	@EJB
	private ManagerClub managerClub;
	@EJB
	private ManagerRoles managerRol;
	@EJB
	private ManagerCarrera managerCarrera;
	
	private Integer idNivel;
	private Integer idClub;
	private Integer idRol;
	private Integer idCarrera;
	private List<Usuario> lista;
	private Usuario usuario;
	private boolean panelColapsado;
	private Usuario selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerUsuario.findAll();
		usuario = new Usuario();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
//			managerCarrera.insertar(carrera);
			managerUsuario.insertar(usuario, idNivel,idRol,
					idCarrera,idClub);
			lista = managerUsuario.findAll();
			usuario = new Usuario();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerInsertarUsuario() {
		try {
//			managerCarrera.insertar(carrera);
			managerUsuario.insertar(usuario, idNivel,2,
					idCarrera,idClub);
			lista = managerUsuario.findAll();
			usuario = new Usuario();
			JSFUtil.createMensajeInfo("insertados");
			
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerUsuario.eliminar(id);
		lista=managerUsuario.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Usuario usuario) {
		selecionada = usuario;
	}
	
	public void actionListenerActualizar() {
		try {
			managerUsuario.actualizar(selecionada);
			lista=managerUsuario.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Usuario getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Usuario selecionada) {
		this.selecionada = selecionada;
	}



	
	public List<SelectItem> getListaCarreraSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Carrera> listadoClientes=managerCarrera.findAll();
		
		for(Carrera c:listadoClientes){
			SelectItem item=new SelectItem(c.getCaId() ,
					                   c.getCaDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	public List<SelectItem> getListaClubSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Club> listadoClientes=managerClub.findAll();
		
		for(Club c:listadoClientes){
			SelectItem item=new SelectItem(c.getCluId() ,
					                   c.getCluDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	public List<SelectItem> getListaNivelSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Nivel> listadoClientes=managerNivel.findAll();
		
		for(Nivel c:listadoClientes){
			SelectItem item=new SelectItem(c.getNiId() ,
					                   c.getNiDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	
	public List<SelectItem> getListaRolSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Role> listadoClientes=managerRol.findAll();
		
		for(Role c:listadoClientes){
			SelectItem item=new SelectItem(c.getRId() ,
					                   c.getRDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	
//	
//	public List<SelectItem> getListaRolesSI(){
//		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
//		List<Role> listadoClientes=managerRol.findAll();
//		
//		for(Role c:listadoClientes){
//			SelectItem item=new SelectItem(c.getCaId() ,
//					                   c.getCaDescripcion());
//			listadoSI.add(item);
//		}
//		return listadoSI;
//	}
	
	
	

	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	public Integer getIdClub() {
		return idClub;
	}

	public void setIdClub(Integer idClub) {
		this.idClub = idClub;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}


}
