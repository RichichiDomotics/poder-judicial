/**
*
* Nombre del Programa : TelefonoDTO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de Telefono entre la vista y servicios.                      
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

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * @author CesarAgustin
 * @version 1.0
 */
public class TelefonoDTO extends MedioDeContactoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7987753895316087190L;

	private Long telefonoId;
	private ValorDTO valorTipoTelefono;
	private String codigoPais;
	private String codigoArea;
	private String numeroTelefonico;
	
	/**
	 * 
	 * @param codigoPais
	 * @param codigoArea
	 * @param numeroTelefonico
	 */
	public TelefonoDTO(String codigoPais, String codigoArea,
			String numeroTelefonico, ValorDTO tipoTelefono) {
		super();		
		this.codigoPais = codigoPais;
		this.codigoArea = codigoArea;
		this.numeroTelefonico = numeroTelefonico;
		this.valorTipoTelefono = tipoTelefono;
	}
	
	public TelefonoDTO() {
		super();				
	}
	
	/**
	 * @return the telefonoId
	 */
	public Long getTelefonoId() {
		return telefonoId;
	}
	/**
	 * @param telefonoId the telefonoId to set
	 */
	public void setTelefonoId(Long telefonoId) {
		this.telefonoId = telefonoId;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}
	/**
	 * @param codigoArea the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}
	/**
	 * @return the numeroTelefonico
	 */
	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}
	/**
	 * @param numeroTelefonico the numeroTelefonico to set
	 */
	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	/**
	 * Método de acceso al campo valorTipoTelefono.
	 * @return El valor del campo valorTipoTelefono
	 */
	public ValorDTO getValorTipoTelefono() {
		return valorTipoTelefono;
	}

	/**
	 * Asigna el valor al campo valorTipoTelefono.
	 * @param valorTipoTelefono el valor valorTipoTelefono a asignar
	 */
	public void setValorTipoTelefono(ValorDTO valorTipoTelefono) {
		this.valorTipoTelefono = valorTipoTelefono;
	}
	
	
	

}
