package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the peticion database table.
 * 
 */
@Entity
@NamedQuery(name="Peticion.findAll", query="SELECT p FROM Peticion p")
public class Peticion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PETICION_PTCID_GENERATOR", sequenceName="SEQ_PETICION",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PETICION_PTCID_GENERATOR")
	@Column(name="ptc_id")
	private long ptcId;

	@Column(name="est_id_estados")
	private BigDecimal estIdEstados;

	@Column(name="ge_id_guia_entrenamiento")
	private BigDecimal geIdGuiaEntrenamiento;

	@Column(name="ptc_fecha")
	private String ptcFecha;

	@Column(name="ptc_hora_fin")
	private String ptcHoraFin;

	@Column(name="ptc_hora_inicio")
	private String ptcHoraInicio;

	@Column(name="tp_id_tipo_peticion")
	private BigDecimal tpIdTipoPeticion;

	@Column(name="user_id_usuario")
	private BigDecimal userIdUsuario;

	public Peticion() {
	}

	public long getPtcId() {
		return this.ptcId;
	}

	public void setPtcId(long ptcId) {
		this.ptcId = ptcId;
	}

	public BigDecimal getEstIdEstados() {
		return this.estIdEstados;
	}

	public void setEstIdEstados(BigDecimal estIdEstados) {
		this.estIdEstados = estIdEstados;
	}

	public BigDecimal getGeIdGuiaEntrenamiento() {
		return this.geIdGuiaEntrenamiento;
	}

	public void setGeIdGuiaEntrenamiento(BigDecimal geIdGuiaEntrenamiento) {
		this.geIdGuiaEntrenamiento = geIdGuiaEntrenamiento;
	}

	public String getPtcFecha() {
		return this.ptcFecha;
	}

	public void setPtcFecha(String ptcFecha) {
		this.ptcFecha = ptcFecha;
	}

	public String getPtcHoraFin() {
		return this.ptcHoraFin;
	}

	public void setPtcHoraFin(String ptcHoraFin) {
		this.ptcHoraFin = ptcHoraFin;
	}

	public String getPtcHoraInicio() {
		return this.ptcHoraInicio;
	}

	public void setPtcHoraInicio(String ptcHoraInicio) {
		this.ptcHoraInicio = ptcHoraInicio;
	}

	public BigDecimal getTpIdTipoPeticion() {
		return this.tpIdTipoPeticion;
	}

	public void setTpIdTipoPeticion(BigDecimal tpIdTipoPeticion) {
		this.tpIdTipoPeticion = tpIdTipoPeticion;
	}

	public BigDecimal getUserIdUsuario() {
		return this.userIdUsuario;
	}

	public void setUserIdUsuario(BigDecimal userIdUsuario) {
		this.userIdUsuario = userIdUsuario;
	}

}