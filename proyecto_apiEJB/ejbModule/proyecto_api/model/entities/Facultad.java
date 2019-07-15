package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the facultad database table.
 * 
 */
@Entity
@NamedQuery(name="Facultad.findAll", query="SELECT f FROM Facultad f")
public class Facultad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FACULTAD_FID_GENERATOR", sequenceName="SEQ_FACULTAD",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACULTAD_FID_GENERATOR")
	@Column(name="f_id")
	private Integer fId;

	@Column(name="f_acronimo")
	private String fAcronimo;

	@Column(name="f_descripcion")
	private String fDescripcion;

	//bi-directional many-to-one association to Carrera
	@OneToMany(mappedBy="facultad",cascade=CascadeType.ALL)
	private List<Carrera> carreras;

	public Facultad() {
	}

	public Integer getFId() {
		return this.fId;
	}

	public void setFId(Integer fId) {
		this.fId = fId;
	}

	public String getFAcronimo() {
		return this.fAcronimo;
	}

	public void setFAcronimo(String fAcronimo) {
		this.fAcronimo = fAcronimo;
	}

	public String getFDescripcion() {
		return this.fDescripcion;
	}

	public void setFDescripcion(String fDescripcion) {
		this.fDescripcion = fDescripcion;
	}

	public List<Carrera> getCarreras() {
		return this.carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public Carrera addCarrera(Carrera carrera) {
		getCarreras().add(carrera);
		carrera.setFacultad(this);

		return carrera;
	}

	public Carrera removeCarrera(Carrera carrera) {
		getCarreras().remove(carrera);
		carrera.setFacultad(null);

		return carrera;
	}

}