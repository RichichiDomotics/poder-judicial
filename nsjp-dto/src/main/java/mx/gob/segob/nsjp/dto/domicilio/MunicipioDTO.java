/**
* Nombre del Programa : MunicipioDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto municipio
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto municipio.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class MunicipioDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4853825621462911985L;
	
	private Long municipioId;
	private String nombreMunicipio;
	private EntidadFederativaDTO entidadFederativaDTO;
	
	
		
	public MunicipioDTO() {
		super();		
	}
	/**
	 * Constructor mínimo.
	 * @param idMpio
	 */
	public MunicipioDTO(Long idMpio) {
	    this.municipioId = idMpio;
	}
	
	/**
	 * @param municipioId
	 * @param nombreMunicipio
	 * @param catEntidadFederativaDTO
	 */
	public MunicipioDTO(Long municipioId, String nombreMunicipio,
			EntidadFederativaDTO entidadFederativaDTO) {
		super();
		this.municipioId = municipioId;
		this.nombreMunicipio = nombreMunicipio;
		this.entidadFederativaDTO = entidadFederativaDTO;
	}
	/**
	 * Método de acceso al campo municipioId.
	 * @return El valor del campo municipioId
	 */
	public Long getMunicipioId() {
		return municipioId;
	}
	/**
	 * Asigna el valor al campo municipioId.
	 * @param municipioId el valor municipioId a asignar
	 */
	public void setMunicipioId(Long municipioId) {
		this.municipioId = municipioId;
	}
	/**
	 * Método de acceso al campo nombreMunicipio.
	 * @return El valor del campo nombreMunicipio
	 */
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	/**
	 * Asigna el valor al campo nombreMunicipio.
	 * @param nombreMunicipio el valor nombreMunicipio a asignar
	 */
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	/**
	 * Método de acceso al campo catEntidadFederativaDTO.
	 * @return El valor del campo catEntidadFederativaDTO
	 */
	public EntidadFederativaDTO getEntidadFederativaDTO() {
		return entidadFederativaDTO;
	}
	/**
	 * Asigna el valor al campo catEntidadFederativaDTO.
	 * @param catEntidadFederativaDTO el valor catEntidadFederativaDTO a asignar
	 */
	public void setEntidadFederativaDTO(
			EntidadFederativaDTO entidadFederativaDTO) {
		this.entidadFederativaDTO = entidadFederativaDTO;
	}
	

	
}
