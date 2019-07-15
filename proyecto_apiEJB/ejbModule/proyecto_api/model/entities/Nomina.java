package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nomina database table.
 * 
 */
@Entity
@NamedQuery(name="Nomina.findAll", query="SELECT n FROM Nomina n")
public class Nomina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOMINA_NMID_GENERATOR", sequenceName="SEQ_NOMINA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOMINA_NMID_GENERATOR")
	@Column(name="nm_id")
	private Integer nmId;

	//bi-directional many-to-one association to Peticione
	@ManyToOne
	@JoinColumn(name="ptc_id_peticiones")
	private Peticione peticione;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="user_id_usuario")
	private Usuario usuario;

	public Nomina() {
	}

	public Integer getNmId() {
		return this.nmId;
	}

	public void setNmId(Integer nmId) {
		this.nmId = nmId;
	}

	public Peticione getPeticione() {
		return this.peticione;
	}

	public void setPeticione(Peticione peticione) {
		this.peticione = peticione;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}