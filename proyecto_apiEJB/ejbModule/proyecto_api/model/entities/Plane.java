package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the planes database table.
 * 
 */
@Entity
@Table(name="planes")
@NamedQuery(name="Plane.findAll", query="SELECT p FROM Plane p")
public class Plane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PLANES_PLID_GENERATOR", sequenceName="SEQ_PLANES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLANES_PLID_GENERATOR")
	@Column(name="pl_id")
	private Integer plId;

	@Column(name="pl_tipo")
	private String plTipo;

	//bi-directional many-to-one association to GuiaEntrenamiento
	@ManyToOne
	@JoinColumn(name="ge_id_guia_entrenamiento")
	private GuiaEntrenamiento guiaEntrenamiento;

	//bi-directional one-to-one association to Rutina
	@OneToOne(mappedBy="plane1")
	private Rutina rutina;

	//bi-directional many-to-one association to Rutina
	@OneToMany(mappedBy="plane2")
	private List<Rutina> rutinas;

	public Plane() {
	}

	public Integer getPlId() {
		return this.plId;
	}

	public void setPlId(Integer plId) {
		this.plId = plId;
	}

	public String getPlTipo() {
		return this.plTipo;
	}

	public void setPlTipo(String plTipo) {
		this.plTipo = plTipo;
	}

	public GuiaEntrenamiento getGuiaEntrenamiento() {
		return this.guiaEntrenamiento;
	}

	public void setGuiaEntrenamiento(GuiaEntrenamiento guiaEntrenamiento) {
		this.guiaEntrenamiento = guiaEntrenamiento;
	}

	public Rutina getRutina() {
		return this.rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	public List<Rutina> getRutinas() {
		return this.rutinas;
	}

	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	public Rutina addRutina(Rutina rutina) {
		getRutinas().add(rutina);
		rutina.setPlane2(this);

		return rutina;
	}

	public Rutina removeRutina(Rutina rutina) {
		getRutinas().remove(rutina);
		rutina.setPlane2(null);

		return rutina;
	}

}