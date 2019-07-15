package proyecto_apiWeb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;


import proyecto_api.model.entities.Facultad;
import proyecto_api.model.entities.Carrera;
import proyecto_api.model.manager.ManagerCarrera;
import proyecto_api.model.manager.ManagerFacultad;
import proyecto_api.model.manager.ManagerPrueba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanCarrera implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerCarrera managerCarrera;
	@EJB
	private ManagerFacultad managerFacultad;
	private Integer idFacultad;
	private List<Carrera> lista;
	private Carrera carrera;
	private boolean panelColapsado;
	private Carrera selecionada;

	@PostConstruct
	public void inicalizar() {
		lista = managerCarrera.findAll();
		carrera = new Carrera();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		try {
//			managerCarrera.insertar(carrera);
			managerCarrera.insertar2(carrera, idFacultad);
			lista = managerCarrera.findAll();
			carrera = new Carrera();
			JSFUtil.createMensajeInfo("insertados");
		} catch (Exception e) {
			JSFUtil.createMensajeError("error");
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminar(Integer id) {
		managerCarrera.eliminar(id);
		lista=managerCarrera.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}
	
	public void actionListenerSeleccionado(Carrera carrera) {
		selecionada = carrera;
	}
	
	public void actionListenerActualizar() {
		try {
			managerCarrera.actualizar(selecionada);
			lista=managerCarrera.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Carrera> getLista() {
		return lista;
	}

	public void setLista(List<Carrera> lista) {
		this.lista = lista;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Carrera getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Carrera selecionada) {
		this.selecionada = selecionada;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	
	public List<SelectItem> getListaClientesSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Facultad> listadoClientes=managerFacultad.findAll();
		
		for(Facultad c:listadoClientes){
			SelectItem item=new SelectItem(c.getFId(), 
					                   c.getFDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public Integer getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(Integer idFacultad) {
		this.idFacultad = idFacultad;
	}
}
