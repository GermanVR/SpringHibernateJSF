package com.german.jsf.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//@Data
/**
 * @author darqko
 *
 */
@Entity
@Data
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString(exclude{"persona"})
@Table(name = "direccion")
// @EqualsAndHashCode(exclude = { "persona" })
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_DIRECCION")
	private long idDireccion;

	// @ManyToOne
	// @JoinColumn(name = "ID_PERSONA") // Nombre de la columna en la DB
	@Column(name = "id_persona")
	// private Persona persona;
	private long idPersona;

	private String ciudad;

	@Column(name = "CODIGO_POSTAL")
	private String codigoPostal;

	private String municipio;

	private String colonia;

	private String telefono;

	private String calle;

	@Override
	public String toString() {
		return "Direccion [idDireccion=" + idDireccion + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + ", municipio=" + municipio + ", colonia=" + colonia + ", telefono=" + telefono + ", calle=" + calle + "]";
	}

}
