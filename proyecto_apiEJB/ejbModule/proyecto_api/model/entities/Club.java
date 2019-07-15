package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the club database table.
 * 
 */
@Entity
@NamedQuery(name="Club.findAll", query="SELECT c FROM Club c")
public class Club implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_CLUID_GENERATOR", sequenceName="SEQ_CLUB",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_CLUID_GENERATOR")
	@Column(name="clu_id")
	private Integer cluId;

	@Column(name="clu_descripcion")
	private String cluDescripcion;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="club",cascade=CascadeType.ALL)
	private List<Usuario> usuarios;

	public Club() {
	}

	public Integer getCluId() {
		return this.cluId;
	}

	public void setCluId(Integer cluId) {
		this.cluId = cluId;
	}

	public String getCluDescripcion() {
		return this.cluDescripcion;
	}

	public void setCluDescripcion(String cluDescripcion) {
		this.cluDescripcion = cluDescripcion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setClub(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setClub(null);

		return usuario;
	}

}