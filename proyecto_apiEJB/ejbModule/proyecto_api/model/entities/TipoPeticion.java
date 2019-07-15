package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_peticion database table.
 * 
 */
@Entity
@Table(name="tipo_peticion")
@NamedQuery(name="TipoPeticion.findAll", query="SELECT t FROM TipoPeticion t")
public class TipoPeticion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_PETICION_TPID_GENERATOR", sequenceName="SEQ_TIPO_PETICION",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PETICION_TPID_GENERATOR")
	@Column(name="tp_id")
	private Integer tpId;

	@Column(name="tp_descripcion")
	private String tpDescripcion;

	//bi-directional many-to-one association to Peticione
	@OneToMany(mappedBy="tipoPeticion",cascade=CascadeType.ALL)
	private List<Peticione> peticiones;

	public TipoPeticion() {
	}

	public Integer getTpId() {
		return this.tpId;
	}

	public void setTpId(Integer tpId) {
		this.tpId = tpId;
	}

	public String getTpDescripcion() {
		return this.tpDescripcion;
	}

	public void setTpDescripcion(String tpDescripcion) {
		this.tpDescripcion = tpDescripcion;
	}

	public List<Peticione> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticione> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticione addPeticione(Peticione peticione) {
		getPeticiones().add(peticione);
		peticione.setTipoPeticion(this);

		return peticione;
	}

	public Peticione removePeticione(Peticione peticione) {
		getPeticiones().remove(peticione);
		peticione.setTipoPeticion(null);

		return peticione;
	}

}