package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLES_RID_GENERATOR", sequenceName="SEQ_ROLES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLES_RID_GENERATOR")
	@Column(name="r_id")
	private Integer rId;

	@Column(name="r_descripcion")
	private String rDescripcion;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="role")
	private List<Usuario> usuarios;

	public Role() {
	}

	public Integer getRId() {
		return this.rId;
	}

	public void setRId(Integer rId) {
		this.rId = rId;
	}

	public String getRDescripcion() {
		return this.rDescripcion;
	}

	public void setRDescripcion(String rDescripcion) {
		this.rDescripcion = rDescripcion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRole(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRole(null);

		return usuario;
	}

}