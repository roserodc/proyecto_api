package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados database table.
 * 
 */
@Entity
@Table(name="estados")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTADOS_ESTID_GENERATOR", sequenceName="SEQ_ESTADOS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTADOS_ESTID_GENERATOR")
	@Column(name="est_id")
	private Integer estId;

	@Column(name="est_descripcion")
	private String estDescripcion;

	//bi-directional many-to-one association to Peticione
	@OneToMany(mappedBy="estado",cascade=CascadeType.ALL)
	private List<Peticione> peticiones;

	public Estado() {
	}

	public Integer getEstId() {
		return this.estId;
	}

	public void setEstId(Integer estId) {
		this.estId = estId;
	}

	public String getEstDescripcion() {
		return this.estDescripcion;
	}

	public void setEstDescripcion(String estDescripcion) {
		this.estDescripcion = estDescripcion;
	}

	public List<Peticione> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticione> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticione addPeticione(Peticione peticione) {
		getPeticiones().add(peticione);
		peticione.setEstado(this);

		return peticione;
	}

	public Peticione removePeticione(Peticione peticione) {
		getPeticiones().remove(peticione);
		peticione.setEstado(null);

		return peticione;
	}

}