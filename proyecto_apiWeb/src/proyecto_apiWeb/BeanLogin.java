package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import proyecto_api.model.dto.LoginDTO;
import proyecto_api.model.entities.Usuario;

import proyecto_api.model.manager.ManagerUsuario;
import proyecto_api.model.manager.ManagerAuditoria;

import java.io.IOException;
import java.io.Serializable;

@Named
@javax.enterprise.context.SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigoUsuario;
	private String clave;
	private String tipoUsuario;
	private boolean acceso;
	@EJB
	private ManagerUsuario managerUsuario;
	
	@EJB
	private ManagerAuditoria managerAuditoria;
	
	private LoginDTO loginDTO;

	@PostConstruct
	public void inicializar() {
		loginDTO=new LoginDTO();
	}
	/**
	 * Action que permite el acceso al sistema.
	 * @return
	 */
	public String accederSistema(){
		acceso=false;
		try {
			
			System.out.println("codigo "+ codigoUsuario);
			System.out.println("clave "+ clave);
			loginDTO=managerUsuario.accederSistema(codigoUsuario, clave);
			//verificamos el acceso del usuario:
			
			System.out.println("login "+ loginDTO.getCodigoUsuario());
			System.out.println("login "+ loginDTO.getRutaAcceso());
			System.out.println("login "+ loginDTO.getTipoUsuario());
			
			tipoUsuario=loginDTO.getTipoUsuario();
			//redireccion dependiendo del tipo de usuario:
			managerAuditoria.crearEvento(loginDTO.getUsuario(), this.getClass(), "accederSistema", "Iniciar Sesion");
			return loginDTO.getRutaAcceso()+"?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createMensajeError(e.getMessage());
			
		}
		return "";
	}
	
	/**
	 * Finaliza la sesion web del usuario.
	 * @return
	 */
	public String salirSistema(){
		System.out.println("salirSistema");
		try {
			managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(), this.getClass(), "salirSistema", "Cerrar sesion");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.html?faces-redirect=true";
	}
	
	public void actionVerificarLogin(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath=ec.getRequestPathInfo();
		try {
			//si no paso por login:
			
			
			if(loginDTO==null || loginDTO.getRutaAcceso()==null || loginDTO.getRutaAcceso().length()==0){
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			}else{
				//validar las rutas de acceso:
				if(requestPath.contains("/usuario") && loginDTO.getRutaAcceso().startsWith("/usuario"))
					return;
				if(requestPath.contains("/instructor") && loginDTO.getRutaAcceso().startsWith("/instructor"))
					return;
				if(requestPath.contains("/admin") && loginDTO.getRutaAcceso().startsWith("/admin"))
					return;
				//caso contrario significa que hizo login pero intenta acceder a ruta no permitida:
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}

