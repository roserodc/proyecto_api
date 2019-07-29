package proyecto_apiWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import proyecto_api.model.entities.Bitacora;
import proyecto_api.model.manager.ManagerAuditoria;
import proyecto_api.model.manager.ManagerPeticion;
import proyecto_api.model.manager.ManagerUsuario;
import proyecto_api.model.manager.ManagerNomina;
import proyecto_api.model.entities.Carrera;
import proyecto_api.model.entities.Estado;
import proyecto_api.model.entities.Peticione;
import proyecto_api.model.entities.Nomina;

@Named
@SessionScoped

public class BeanBitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerAuditoria managerAuditoria;
	
	private List<Bitacora> lista;
	
	
	@PostConstruct
	public void inicalizar() {
		lista = managerAuditoria.findAll();
	}


	public List<Bitacora> getLista() {
		return lista;
	}


	public void setLista(List<Bitacora> lista) {
		this.lista = lista;
	}

	

}
