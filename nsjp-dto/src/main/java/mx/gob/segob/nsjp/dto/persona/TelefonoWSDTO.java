/**
*
* Nombre del Programa : TelefonoWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos b�sicos de un Telefono.                      
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

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * @author GustavoBP
 * @version 1.0
 */
public class TelefonoWSDTO extends GenericWSDTO {

	
	private Long valorTipoTelefonoId;
	private String codigoPais;
	private String codigoArea;
	private String numeroTelefonico;
	
	/**
	 * 
	 * @param codigoPais
	 * @param codigoArea
	 * @param numeroTelefonico
	 */
	public TelefonoWSDTO(String codigoPais, String codigoArea,
			String numeroTelefonico, Long tipoTelefonoId) {
		super();		
		this.codigoPais = codigoPais;
		this.codigoArea = codigoArea;
		this.numeroTelefonico = numeroTelefonico;
		this.valorTipoTelefonoId = tipoTelefonoId;
	}
	
	public TelefonoWSDTO() {
		super();				
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
	 * M�todo de acceso al campo valorTipoTelefonoId.
	 * @return El valor del campo valorTipoTelefonoId
	 */
	public Long getValorTipoTelefonoId() {
		return valorTipoTelefonoId;
	}

	/**
	 * Asigna el valor al campo valorTipoTelefonoId.
	 * @param valorTipoTelefono el valor valorTipoTelefonoId a asignar
	 */
	public void setValorTipoTelefonoId(Long valorTipoTelefono) {
		this.valorTipoTelefonoId = valorTipoTelefono;
	}
	
	
	

}
