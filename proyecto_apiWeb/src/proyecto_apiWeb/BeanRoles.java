package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_api.model.entities.Role;
import proyecto_api.model.manager.ManagerRoles;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanRoles implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerRoles managerRoles;
	private List<Role> lista;
	private Role roles;
	private boolean panelColapsado;
	private Role rolesSeleccionados;
	
	@PostConstruct
	public void inicalizar() {
		lista = managerRoles.findAll();
		roles = new Role();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
			managerRoles.insertar(roles);
			lista=managerRoles.findAll();
			roles=new Role();
			JSFUtil.createMensajeInfo("Datos Insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerRoles.eliminar(id);
		lista=managerRoles.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Role roles) {
		rolesSeleccionados=roles;
	}
	
	public void actionListenerActualizar() {
		try {
			managerRoles.actualizar(rolesSeleccionados);
			lista=managerRoles.findAll();
			JSFUtil.createMensajeInfo("Actualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Role> getLista() {
		return lista;
	}

	public void setLista(List<Role> lista) {
		this.lista = lista;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Role getRolesSeleccionados() {
		return rolesSeleccionados;
	}

	public void setRolesSeleccionados(Role rolesSeleccionados) {
		this.rolesSeleccionados = rolesSeleccionados;
	}
	
	
}
