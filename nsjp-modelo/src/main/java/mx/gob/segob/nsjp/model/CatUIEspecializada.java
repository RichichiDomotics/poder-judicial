/**
* Nombre del Programa : CatUIEspecializada.java
* Autor                            : AdrianECH
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/03/2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catalogo de unidades de investigacion especializadas.
 * Fecha: 05/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
@Entity
@Table(name = "CatUIEspecializada")
public class CatUIEspecializada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 926125121205713610L;
	private Long catUIEId;
    private String claveUIE;
    private String nombreUIE;
	/**
	 * Método de acceso al campo catUIEId.
	 * @return El valor del campo catUIEId
	 */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catUIE_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatUIEId() {
		return catUIEId;
	}
	/**
	 * Método de acceso al campo claveUIE.
	 * @return El valor del campo claveUIE
	 */
	@Column(name = "cClaveUIE", unique = true, nullable = false, length = 10)
	public String getClaveUIE() {
		return claveUIE;
	}
	/**
	 * Método de acceso al campo nombreUIE.
	 * @return El valor del campo nombreUIE
	 */
	@Column(name = "cNombreUIE", nullable = false, length = 150)
	public String getNombreUIE() {
		return nombreUIE;
	}
	/**
	 * Asigna el valor al campo catUIEId.
	 * @param catUIEId el valor catUIEId a asignar
	 */
	public void setCatUIEId(Long catUIEId) {
		this.catUIEId = catUIEId;
	}
	/**
	 * Asigna el valor al campo claveUIE.
	 * @param claveUIE el valor claveUIE a asignar
	 */
	public void setClaveUIE(String claveUIE) {
		this.claveUIE = claveUIE;
	}
	/**
	 * Asigna el valor al campo nombreUIE.
	 * @param nombreUIE el valor nombreUIE a asignar
	 */
	public void setNombreUIE(String nombreUIE) {
		this.nombreUIE = nombreUIE;
	}
}
