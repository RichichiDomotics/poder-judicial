/**
* Nombre del Programa : CalidadDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/03/2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto MedioDeContacto.
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
package mx.gob.segob.nsjp.dto.persona;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class MedioDeContactoDTO extends GenericDTO {
	
	private static final long serialVersionUID = 3239082518166250948L;	
	
	private Long medioDeContadoId;
	private PersonaDTO personaDTO;
	
	/**
	 * 
	 */
	public MedioDeContactoDTO() {
		
	}
	

	/**
	 * Método de acceso al campo medioDeContadoId.
	 * @return El valor del campo medioDeContadoId
	 */
	public Long getMedioDeContadoId() {
		return medioDeContadoId;
	}



	/**
	 * Asigna el valor al campo medioDeContadoId.
	 * @param medioDeContadoId el valor medioDeContadoId a asignar
	 */
	public void setMedioDeContadoId(Long medioDeContadoId) {
		this.medioDeContadoId = medioDeContadoId;
	}



	/**
	 * Método de acceso al campo personaDTO.
	 * @return El valor del campo personaDTO
	 */
	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}

	/**
	 * Asigna el valor al campo personaDTO.
	 * @param personaDTO el valor personaDTO a asignar
	 */
	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}
	
	
}
