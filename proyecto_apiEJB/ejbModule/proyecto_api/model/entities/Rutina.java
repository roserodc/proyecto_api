package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rutinas database table.
 * 
 */
@Entity
@Table(name="rutinas")
@NamedQuery(name="Rutina.findAll", query="SELECT r FROM Rutina r")
public class Rutina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RUTINAS_RTID_GENERATOR", sequenceName="SEQ_RUTINAS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RUTINAS_RTID_GENERATOR")
	@Column(name="rt_id")
	private Integer rtId;

	@Column(name="rt_descripcion")
	private String rtDescripcion;

	@Column(name="rt_duracion")
	private String rtDuracion;

	@Column(name="rt_repeticiones")
	private Integer rtRepeticiones;

	@Column(name="rt_series")
	private Integer rtSeries;

	//bi-directional one-to-one association to Plane
	@OneToOne
	@JoinColumn(name="rt_id")
	private Plane plane1;

	//bi-directional many-to-one association to Plane
	@ManyToOne
	@JoinColumn(name="pl_id_planes")
	private Plane plane2;

	public Rutina() {
	}

	public Integer getRtId() {
		return this.rtId;
	}

	public void setRtId(Integer rtId) {
		this.rtId = rtId;
	}

	public String getRtDescripcion() {
		return this.rtDescripcion;
	}

	public void setRtDescripcion(String rtDescripcion) {
		this.rtDescripcion = rtDescripcion;
	}

	public String getRtDuracion() {
		return this.rtDuracion;
	}

	public void setRtDuracion(String rtDuracion) {
		this.rtDuracion = rtDuracion;
	}

	public Integer getRtRepeticiones() {
		return this.rtRepeticiones;
	}

	public void setRtRepeticiones(Integer rtRepeticiones) {
		this.rtRepeticiones = rtRepeticiones;
	}

	public Integer getRtSeries() {
		return this.rtSeries;
	}

	public void setRtSeries(Integer rtSeries) {
		this.rtSeries = rtSeries;
	}

	public Plane getPlane1() {
		return this.plane1;
	}

	public void setPlane1(Plane plane1) {
		this.plane1 = plane1;
	}

	public Plane getPlane2() {
		return this.plane2;
	}

	public void setPlane2(Plane plane2) {
		this.plane2 = plane2;
	}

}