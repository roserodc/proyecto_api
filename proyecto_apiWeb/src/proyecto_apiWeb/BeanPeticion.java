package proyecto_apiWeb;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import proyecto_api.model.entities.Estado;
import proyecto_api.model.entities.Plane;
import proyecto_api.model.entities.Peticione;
import proyecto_api.model.entities.TipoPeticion;
import proyecto_api.model.entities.Usuario;
import proyecto_api.model.manager.ManagerEstados;
import proyecto_api.model.manager.ManagerPlan;
import proyecto_api.model.manager.ManagerPeticion;
import proyecto_api.model.manager.ManagerTipoPeticion;
import proyecto_api.model.manager.ManagerUsuario;
import proyecto_apiWeb.BeanUsuario;
import proyecto_apiWeb.BeanLogin;

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
	private ManagerPlan managerPlan;

//	@EJB

	private Usuario usuario;
	private Integer idTipoPeticion;
	private Integer idEstado;

	private Integer idUsuario;
	private Integer idPlan;
	private List<Peticione> lista;
	private List<Peticione> listaIndividuales;
	private List<Peticione> listaGrupales;
	private List<Usuario> listaInstructores;
	private List<Usuario> listaUsuarios;
	private Peticione peticion;

	private boolean panelColapsado;
	private Peticione selecionada;

	@Inject
	private BeanLogin beanlogin;

	@Inject
	private BeanNomina beannomina;

	@PostConstruct
	public void inicalizar() {
		peticion = new Peticione();
		panelColapsado = true;
		// lista = managerPeticion.findAll();
		usuario = managerUsuario.findByCedula(Integer.parseInt(beanlogin.getCodigoUsuario()));
		listaIndividuales = managerPeticion.findAllIndividuales();
		listaGrupales = managerPeticion.findAllGrupales();
		listaUsuarios = managerUsuario.findAllUsuarios();
		listaInstructores = managerUsuario.findAllInstructores();
		System.out.println("--------*" + usuario.getUserId());
		lista = managerPeticion.findAll2(usuario.getUserId());

	}

	public void actionListenerColapsarPanerl() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertar() {
		String r = "";
		int tp = 0;
		if (usuario.getRole().getRId() == 1) {
			tp = 1;
		} else {
			tp = 2;
		}

		

		String h1 = peticion.getPtcHoraInicio();
		String[] hi = h1.split(":");
		int hi1 = Integer.parseInt(hi[0]); // 004-
		System.out.println("-----hora inicio---*" + hi1);

		String h2 = peticion.getPtcHoraFin();
		String[] hf = h2.split(":");
		int hf1 = Integer.parseInt(hf[0]); // 004-
		System.out.println("-----hora fin---*" + hf1);

		if (hi1 > hf1 || peticion.getPtcHoraInicio().equals(peticion.getPtcHoraFin())) {
			System.out.println("equasl" + usuario.getUserId());
			JSFUtil.createMensajeError("Revise campos de hora, hora inicio debe ser menor a hora fin");
		}
		if (peticion.getPtcFecha() == null) {
			JSFUtil.createMensajeError("Debe seleccionar una fecha");
		}
		if (idPlan == null) {
			JSFUtil.createMensajeError("Debe seleccionar un plan de la lista");
		} else {

			try {
				System.out.println("----insert-try---*" + usuario.getUserId());
				managerPeticion.insertar2(peticion, tp, 3, usuario.getUserId(), idPlan);
				lista = managerPeticion.findAll2(usuario.getUserId());
				peticion = new Peticione();
				JSFUtil.createMensajeInfo("Petición Registrada");
			} catch (Exception e) {
				System.out.println("----insert- error---*" + usuario.getUserId());
				JSFUtil.createMensajeError("error " + e.getMessage() + " " + e.getCause());
				e.printStackTrace();
			}

		}
	}

	public void actionListenerEliminar(Integer id) {
		managerPeticion.eliminar(id);
		lista = managerPeticion.findAll();
		JSFUtil.createMensajeInfo("Eliminado");
	}

	public void actionListenerSeleccionado(Peticione peticion) {
		listaUsuarios = managerUsuario.findAllUsuarios();
		selecionada = peticion;

	}

	public void actionListenerSelecAddUser(Peticione peticion) {
		selecionada = peticion;
		beannomina.ViewUsuarioxPeticion(peticion.getPtcId());

	}

	public void actionListenerActualizar() {
		try {
			managerPeticion.actualizar(selecionada);
			lista = managerPeticion.findAll();
			JSFUtil.createMensajeInfo("Acualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerAceptarPeticion(Integer idPtc) {
		try {

			managerPeticion.actualizarAceptar(idPtc);
			lista = managerPeticion.findAll();
			JSFUtil.createMensajeInfo("Aceptado");
			listaIndividuales = managerPeticion.findAllIndividuales();
			listaGrupales = managerPeticion.findAllGrupales();
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerRechazarPeticion(Integer idPtc) {
		try {

			managerPeticion.actualizarRechazar(idPtc);
			lista = managerPeticion.findAll();
			JSFUtil.createMensajeInfo("Rechazado");
			listaIndividuales = managerPeticion.findAllIndividuales();
			listaGrupales = managerPeticion.findAllGrupales();
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionReporteUsuario() {
		Map<String, Object> parametros = new HashMap<String, Object>();
//		/*
		parametros.put("user_id_usuario", usuario.getUserId());
//		 parametros.put("p_titulo",p_titulo);
//		 */
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("usuario/reporteUser.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=reporte.pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gimnasio_utn", "postgres",
					"dr1234");
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			System.out.println("reporte generado.");
			context.responseComplete();
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public List<Peticione> getLista() {
		List<Peticione> listadoPeticiones = managerPeticion.findAll();
		return listadoPeticiones;
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

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public List<SelectItem> getListaTipoPeticionSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<TipoPeticion> listadoClientes = managerTipoPeticion.findAll();

		for (TipoPeticion c : listadoClientes) {
			SelectItem item = new SelectItem(c.getTpId(), c.getTpDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public List<SelectItem> getListaEstadoSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Estado> listadoClientes = managerEstado.findAll();

		for (Estado c : listadoClientes) {
			SelectItem item = new SelectItem(c.getEstId(), c.getEstDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public List<SelectItem> getListaUsuarioSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Usuario> listadoClientes = managerUsuario.findAll();

		for (Usuario c : listadoClientes) {
			SelectItem item = new SelectItem(c.getUserId(), c.getUserNombre() + " " + c.getUserApellido());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public List<SelectItem> getListaPlanSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Plane> listadoPlan = managerPlan.findAll();

		for (Plane c : listadoPlan) {
			SelectItem item = new SelectItem(c.getPlId(), c.getPlTipo());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public List<Peticione> getListaIndividuales() {
		List<Peticione> listadoPeticionesIndividuales = managerPeticion.findAllIndividuales();
		return listadoPeticionesIndividuales;
	}

	public void setListaIndividuales(List<Peticione> listaIndividuales) {
		this.listaIndividuales = listaIndividuales;
	}

	public List<Peticione> getListaGrupales() {
		List<Peticione> listadoPeticionesGrupales = managerPeticion.findAllGrupales();
		return listadoPeticionesGrupales;
	}

	public List<Usuario> getListaInstructores() {
		return listaInstructores;
	}

	public void setListaInstructores(List<Usuario> listaInstructores) {
		this.listaInstructores = listaInstructores;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public void setListaGrupales(List<Peticione> listaGrupales) {
		this.listaGrupales = listaGrupales;
	}

}
