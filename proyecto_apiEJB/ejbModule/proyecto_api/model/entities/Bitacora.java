package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bitacora database table.
 * 
 */
@Entity
@NamedQuery(name="Bitacora.findAll", query="SELECT b FROM Bitacora b")
public class Bitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BITACORA_BID_GENERATOR", sequenceName="SEQ_BITACORA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BITACORA_BID_GENERATOR")
	@Column(name="b_id")
	private Integer bId;

	@Column(name="b_descripcion")
	private String bDescripcion;

	@Column(name="b_fechaevento")
	private String bFechaevento;

	@Column(name="b_ipmaquina")
	private String bIpmaquina;

	@Column(name="b_nombreevento")
	private String bNombreevento;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="user_id_usuario")
	private Usuario usuario;

	public Bitacora() {
	}

	public Integer getBId() {
		return this.bId;
	}

	public void setBId(Integer bId) {
		this.bId = bId;
	}

	public String getBDescripcion() {
		return this.bDescripcion;
	}

	public void setBDescripcion(String bDescripcion) {
		this.bDescripcion = bDescripcion;
	}

	public String getBFechaevento() {
		return this.bFechaevento;
	}

	public void setBFechaevento(String bFechaevento) {
		this.bFechaevento = bFechaevento;
	}

	public String getBIpmaquina() {
		return this.bIpmaquina;
	}

	public void setBIpmaquina(String bIpmaquina) {
		this.bIpmaquina = bIpmaquina;
	}

	public String getBNombreevento() {
		return this.bNombreevento;
	}

	public void setBNombreevento(String bNombreevento) {
		this.bNombreevento = bNombreevento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}