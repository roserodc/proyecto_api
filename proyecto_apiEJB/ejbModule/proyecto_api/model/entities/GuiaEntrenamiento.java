package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the guia_entrenamiento database table.
 * 
 */
@Entity
@Table(name="guia_entrenamiento")
@NamedQuery(name="GuiaEntrenamiento.findAll", query="SELECT g FROM GuiaEntrenamiento g")
public class GuiaEntrenamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GUIA_ENTRENAMIENTO_GEID_GENERATOR", sequenceName="SEQ_GUIA_ENTRENAMIENTO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GUIA_ENTRENAMIENTO_GEID_GENERATOR")
	@Column(name="ge_id")
	private Integer geId;

	@Column(name="ge_descripcion")
	private String geDescripcion;

	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy="guiaEntrenamiento")
	private List<Plane> planes;

	//bi-directional many-to-one association to Peticione
	@OneToMany(mappedBy="guiaEntrenamiento")
	private List<Peticione> peticiones;

	public GuiaEntrenamiento() {
	}

	public Integer getGeId() {
		return this.geId;
	}

	public void setGeId(Integer geId) {
		this.geId = geId;
	}

	public String getGeDescripcion() {
		return this.geDescripcion;
	}

	public void setGeDescripcion(String geDescripcion) {
		this.geDescripcion = geDescripcion;
	}

	public List<Plane> getPlanes() {
		return this.planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public Plane addPlane(Plane plane) {
		getPlanes().add(plane);
		plane.setGuiaEntrenamiento(this);

		return plane;
	}

	public Plane removePlane(Plane plane) {
		getPlanes().remove(plane);
		plane.setGuiaEntrenamiento(null);

		return plane;
	}

	public List<Peticione> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticione> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticione addPeticione(Peticione peticione) {
		getPeticiones().add(peticione);
		peticione.setGuiaEntrenamiento(this);

		return peticione;
	}

	public Peticione removePeticione(Peticione peticione) {
		getPeticiones().remove(peticione);
		peticione.setGuiaEntrenamiento(null);

		return peticione;
	}

}