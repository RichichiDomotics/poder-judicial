/**
* Nombre del Programa : CiudadDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto ciudad
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
package mx.gob.segob.nsjp.dto.domicilio;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto ciudad.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class CiudadDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3017222143767076257L;
	
	private Long ciudadId;	
	private String abreviacion;
	private String nombreCiudad;
	private EntidadFederativaDTO entidadFederativaDTO;
	
	/**
	 * @param ciudadId
	 * @param abreviacion
	 * @param nombreCiudad
	 * @param entidadFederativaDTO
	 */
	public CiudadDTO(Long ciudadId, String abreviacion,
			String nombreCiudad, EntidadFederativaDTO entidadFederativaDTO) {
		super();
		this.ciudadId = ciudadId;
		this.abreviacion = abreviacion;
		this.nombreCiudad = nombreCiudad;
		this.entidadFederativaDTO = entidadFederativaDTO;
	}
	
	/**
	 * 
	 */
	public CiudadDTO() {
		super();
	}

	public CiudadDTO(Long ciudadId) {
		this.ciudadId = ciudadId;
	}

	/**
	 * Método de acceso al campo ciudadId.
	 * @return El valor del campo ciudadId
	 */
	public Long getCiudadId() {
		return ciudadId;
	}

	/**
	 * Asigna el valor al campo ciudadId.
	 * @param ciudadId el valor ciudadId a asignar
	 */
	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}

	/**
	 * Método de acceso al campo abreviacion.
	 * @return El valor del campo abreviacion
	 */
	public String getAbreviacion() {
		return abreviacion;
	}

	/**
	 * Asigna el valor al campo abreviacion.
	 * @param abreviacion el valor abreviacion a asignar
	 */
	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	/**
	 * Método de acceso al campo nombreCiudad.
	 * @return El valor del campo nombreCiudad
	 */
	public String getNombreCiudad() {
		return nombreCiudad;
	}

	/**
	 * Asigna el valor al campo nombreCiudad.
	 * @param nombreCiudad el valor nombreCiudad a asignar
	 */
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	/**
	 * Método de acceso al campo entidadFederativaDTO.
	 * @return El valor del campo entidadFederativaDTO
	 */
	public EntidadFederativaDTO getEntidadFederativaDTO() {
		return entidadFederativaDTO;
	}

	/**
	 * Asigna el valor al campo entidadFederativaDTO.
	 * @param entidadFederativaDTO el valor entidadFederativaDTO a asignar
	 */
	public void setEntidadFederativaDTO(EntidadFederativaDTO entidadFederativaDTO) {
		this.entidadFederativaDTO = entidadFederativaDTO;
	}		

}
