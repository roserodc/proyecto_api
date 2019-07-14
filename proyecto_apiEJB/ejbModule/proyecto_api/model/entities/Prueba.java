package proyecto_api.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prueba database table.
 * 
 */
@Entity
@NamedQuery(name="Prueba.findAll", query="SELECT p FROM Prueba p")
public class Prueba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRUEBA_ID_GENERATOR", sequenceName="SEQ_PRUEBA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRUEBA_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	public Prueba() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}