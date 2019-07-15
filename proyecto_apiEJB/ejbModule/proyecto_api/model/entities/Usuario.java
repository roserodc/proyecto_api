package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_USERID_GENERATOR", sequenceName="SEQ_USUARIO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_USERID_GENERATOR")
	@Column(name="user_id")
	private Integer userId;

	@Column(name="user_apellido")
	private String userApellido;

	@Column(name="user_ci")
	private String userCi;

	@Column(name="user_nombre")
	private String userNombre;

	@Column(name="user_pass")
	private String userPass;

	@Column(name="user_telefono")
	private String userTelefono;

	//bi-directional many-to-one association to Carrera
	@ManyToOne
	@JoinColumn(name="ca_id_carrera")
	private Carrera carrera;

	//bi-directional many-to-one association to Club
	@ManyToOne
	@JoinColumn(name="clu_id_club")
	private Club club;

	//bi-directional many-to-one association to Nivel
	@ManyToOne
	@JoinColumn(name="ni_id_nivel")
	private Nivel nivel;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="r_id_roles")
	private Role role;

	//bi-directional many-to-one association to Bitacora
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private List<Bitacora> bitacoras;

	//bi-directional many-to-one association to Nomina
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private List<Nomina> nominas;

	//bi-directional many-to-one association to Peticione
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private List<Peticione> peticiones;

	public Usuario() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserApellido() {
		return this.userApellido;
	}

	public void setUserApellido(String userApellido) {
		this.userApellido = userApellido;
	}

	public String getUserCi() {
		return this.userCi;
	}

	public void setUserCi(String userCi) {
		this.userCi = userCi;
	}

	public String getUserNombre() {
		return this.userNombre;
	}

	public void setUserNombre(String userNombre) {
		this.userNombre = userNombre;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserTelefono() {
		return this.userTelefono;
	}

	public void setUserTelefono(String userTelefono) {
		this.userTelefono = userTelefono;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Nivel getNivel() {
		return this.nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Bitacora> getBitacoras() {
		return this.bitacoras;
	}

	public void setBitacoras(List<Bitacora> bitacoras) {
		this.bitacoras = bitacoras;
	}

	public Bitacora addBitacora(Bitacora bitacora) {
		getBitacoras().add(bitacora);
		bitacora.setUsuario(this);

		return bitacora;
	}

	public Bitacora removeBitacora(Bitacora bitacora) {
		getBitacoras().remove(bitacora);
		bitacora.setUsuario(null);

		return bitacora;
	}

	public List<Nomina> getNominas() {
		return this.nominas;
	}

	public void setNominas(List<Nomina> nominas) {
		this.nominas = nominas;
	}

	public Nomina addNomina(Nomina nomina) {
		getNominas().add(nomina);
		nomina.setUsuario(this);

		return nomina;
	}

	public Nomina removeNomina(Nomina nomina) {
		getNominas().remove(nomina);
		nomina.setUsuario(null);

		return nomina;
	}

	public List<Peticione> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticione> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticione addPeticione(Peticione peticione) {
		getPeticiones().add(peticione);
		peticione.setUsuario(this);

		return peticione;
	}

	public Peticione removePeticione(Peticione peticione) {
		getPeticiones().remove(peticione);
		peticione.setUsuario(null);

		return peticione;
	}

}