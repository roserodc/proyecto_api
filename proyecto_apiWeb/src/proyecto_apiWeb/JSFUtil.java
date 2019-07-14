package proyecto_apiWeb;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	public static void createMensajeInfo(String mensaje) {
		FacesMessage msg=new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public static void createMensajeWarning(String mensaje) {
		FacesMessage msg=new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		msg.setSummary(mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void createMensajeError(String mensaje) {
		FacesMessage msg=new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}



}
