package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;


import proyecto_api.model.entities.Usuario;

import proyecto_api.model.manager.ManagerUsuario;

import java.io.Serializable;

@Named
@javax.enterprise.context.SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUsuario managerUsuario;
	private Usuario usuario;
	@PostConstruct
	public void inicializar() {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String iniciarSesion() {
		Usuario us;
		String redireccion = null;
		try {
			us=managerUsuario.iniciarSesion(usuario);
			if(us!=null) {
				if(us.getRole().getRDescripcion().equals("Usuario")) {
					redireccion = "/usuario/indexUsuario?faces-redirect=true";
				}else if(us.getRole().getRDescripcion().equals("Instructor")) {
					redireccion = "/instructor/indexInstructor?faces-redirect=true";
				}else {
					redireccion = "/admingym/indexAdminGym?faces-redirect=true";
				}
				
			}else {
				JSFUtil.createMensajeWarning("Credenciales incorrectas");
			}

//			System.out.println("----------------------------------"+usuario.getRole().getRId());
			
		}catch(Exception e){
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return redireccion;
	}

	
}
