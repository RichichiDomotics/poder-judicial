/**
* Nombre del Programa : BiometricoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto biometrico.
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
package mx.gob.segob.nsjp.dto.involucrado;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto biometrico.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class BiometricoDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2421788632358984134L;

	private Long biometricoId;	
	private String rasgoBiometrico;
	private ValorDTO valorIdBiometria;
	private InvolucradoDTO involucradoDTO;
	
	/**
	 * 
	 */
	public BiometricoDTO() {
		super();		
	}	

	/**
	 * @param biometricoId
	 * @param rasgoBiometrico
	 * @param valorIdBiometria
	 * @param involucradoDTO
	 */
	public BiometricoDTO(Long biometricoId, String rasgoBiometrico,
			ValorDTO valorIdBiometria, InvolucradoDTO involucradoDTO) {
		super();
		this.biometricoId = biometricoId;
		this.rasgoBiometrico = rasgoBiometrico;
		this.valorIdBiometria = valorIdBiometria;
		this.involucradoDTO = involucradoDTO;
	}

	/**
	 * Método de acceso al campo biometricoId.
	 * @return El valor del campo biometricoId
	 */
	public Long getBiometricoId() {
		return biometricoId;
	}

	/**
	 * Asigna el valor al campo biometricoId.
	 * @param biometricoId el valor biometricoId a asignar
	 */
	public void setBiometricoId(Long biometricoId) {
		this.biometricoId = biometricoId;
	}

	/**
	 * Método de acceso al campo rasgoBiometrico.
	 * @return El valor del campo rasgoBiometrico
	 */
	public String getRasgoBiometrico() {
		return rasgoBiometrico;
	}

	/**
	 * Asigna el valor al campo rasgoBiometrico.
	 * @param rasgoBiometrico el valor rasgoBiometrico a asignar
	 */
	public void setRasgoBiometrico(String rasgoBiometrico) {
		this.rasgoBiometrico = rasgoBiometrico;
	}

	/**
	 * Método de acceso al campo valorIdBiometria.
	 * @return El valor del campo valorIdBiometria
	 */
	public ValorDTO getValorIdBiometria() {
		return valorIdBiometria;
	}

	/**
	 * Asigna el valor al campo valorIdBiometria.
	 * @param valorIdBiometria el valor valorIdBiometria a asignar
	 */
	public void setValorIdBiometria(ValorDTO valorIdBiometria) {
		this.valorIdBiometria = valorIdBiometria;
	}

	/**
	 * Método de acceso al campo involucradoDTO.
	 * @return El valor del campo involucradoDTO
	 */
	public InvolucradoDTO getInvolucradoDTO() {
		return involucradoDTO;
	}

	/**
	 * Asigna el valor al campo involucradoDTO.
	 * @param involucradoDTO el valor involucradoDTO a asignar
	 */
	public void setInvolucradoDTO(InvolucradoDTO involucradoDTO) {
		this.involucradoDTO = involucradoDTO;
	}
	
}
