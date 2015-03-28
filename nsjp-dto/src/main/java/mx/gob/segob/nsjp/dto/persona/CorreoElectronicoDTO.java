/**
*
* Nombre del Programa : CorreoElectronicoDTO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de Correo electronico entre la vista y servicios.                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dto.persona;

/**
 * @author CesarAgustin
 * @version 1.0
 */
public class CorreoElectronicoDTO extends MedioDeContactoDTO {
	
	private Long correoElectronicoId;
	private String direccionElectronica;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3392688726614154654L;
	
	/**
	 * 
	 * @param correoElectronicoId
	 * @param direccionElectronica
	 */
	public CorreoElectronicoDTO(String direccionElectronica) {
		super();		
		this.direccionElectronica = direccionElectronica;
	}

	public CorreoElectronicoDTO() {
		super();				
	}
	
	/**
	 * @return the correoElectronicoId
	 */
	public Long getCorreoElectronicoId() {
		return correoElectronicoId;
	}

	/**
	 * @param correoElectronicoId the correoElectronicoId to set
	 */
	public void setCorreoElectronicoId(Long correoElectronicoId) {
		this.correoElectronicoId = correoElectronicoId;
	}

	/**
	 * @return the direccionElectronica
	 */
	public String getDireccionElectronica() {
		return direccionElectronica;
	}

	/**
	 * @param direccionElectronica the direccionElectronica to set
	 */
	public void setDireccionElectronica(String direccionElectronica) {
		this.direccionElectronica = direccionElectronica;
	}

}
