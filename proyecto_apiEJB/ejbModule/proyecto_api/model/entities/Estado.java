package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;


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
	@SequenceGenerator(name="ESTADOS_ESTID_GENERATOR", sequenceName="SEQ_ESTADOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTADOS_ESTID_GENERATOR")
	@Column(name="est_id")
	private Integer estId;

	@Column(name="est_descripcion")
	private String estDescripcion;

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

}