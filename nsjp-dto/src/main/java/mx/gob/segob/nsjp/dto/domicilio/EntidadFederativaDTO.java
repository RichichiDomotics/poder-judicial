/**
* Nombre del Programa : EntidadFederativaDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto entidadFederativa
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
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto entidadFederativa.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class EntidadFederativaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 634345603647402740L;
	
	private Long entidadFederativaId;			
	private String abreviacion;
	private String czonaGeografica;
	private String nombreEntidad;
	private ValorDTO valorIdPais;
	
	/**
	 * 
	 * @param idEntidadFederativa
	 */
	public EntidadFederativaDTO(Long idEntidadFederativa) {
		super();
		this.entidadFederativaId = idEntidadFederativa;
	}
	/**
	 * 
	 */
	public EntidadFederativaDTO() {
		
	}


	/**
	 * Método de acceso al campo lcatEntidadFederativaId.
	 * @return El valor del campo lcatEntidadFederativaId
	 */
	public Long getEntidadFederativaId() {
		return entidadFederativaId;
	}
	/**
	 * Asigna el valor al campo lcatEntidadFederativaId.
	 * @param lcatEntidadFederativaId el valor lcatEntidadFederativaId a asignar
	 */
	public void setEntidadFederativaId(Long entidadFederativaId) {
		this.entidadFederativaId = entidadFederativaId;
	}
	/**
	 * Método de acceso al campo cabreviacion.
	 * @return El valor del campo cabreviacion
	 */
	public String getAbreviacion() {
		return abreviacion;
	}
	/**
	 * Asigna el valor al campo cabreviacion.
	 * @param cabreviacion el valor cabreviacion a asignar
	 */
	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}
	/**
	 * Método de acceso al campo czonaGeografica.
	 * @return El valor del campo czonaGeografica
	 */
	public String getCzonaGeografica() {
		return czonaGeografica;
	}
	/**
	 * Asigna el valor al campo czonaGeografica.
	 * @param czonaGeografica el valor czonaGeografica a asignar
	 */
	public void setCzonaGeografica(String czonaGeografica) {
		this.czonaGeografica = czonaGeografica;
	}
	/**
	 * Método de acceso al campo valorIdPais.
	 * @return El valor del campo valorIdPais
	 */
	public ValorDTO getValorIdPais() {
		return valorIdPais;
	}
	/**
	 * Asigna el valor al campo valorIdPais.
	 * @param valorIdPais el valor valorIdPais a asignar
	 */
	public void setValorIdPais(ValorDTO valorIdPais) {
		this.valorIdPais = valorIdPais;
	}
	/**
	 * Método de acceso al campo nombreEntidad.
	 * @return El valor del campo nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	/**
	 * Asigna el valor al campo nombreEntidad.
	 * @param nombreEntidad el valor nombreEntidad a asignar
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	
	
}
