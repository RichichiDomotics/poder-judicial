/**
*
* Nombre del Programa : ElementoDTO.java                                    
* Autor                            : rgama                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                     
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de Sesion entre la vista y servicios.                      
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
package mx.gob.segob.nsjp.dto.sesion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;



/**
 * @author rgama
 * @version 1.0
 */
public class SesionDTO extends GenericDTO {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5499681674022242955L;

	private Long sesionId;
	private java.util.Date fechaSesion;
	private Boolean esPresente;
	private Short numeroSesion;
	private ValorDTO tipoSesion;
	private ExpedienteDTO numeroExpediente;
	/**
	 * Método de acceso al campo sesionId.
	 * @return El valor del campo sesionId
	 */
	public Long getSesionId() {
		return sesionId;
	}
	/**
	 * Asigna el valor al campo sesionId.
	 * @param sesionId el valor sesionId a asignar
	 */
	public void setSesionId(Long sesionId) {
		this.sesionId = sesionId;
	}
	/**
	 * Método de acceso al campo fechaSesion.
	 * @return El valor del campo fechaSesion
	 */
	public java.util.Date getFechaSesion() {
		return fechaSesion;
	}
	/**
	 * Asigna el valor al campo fechaSesion.
	 * @param fechaSesion el valor fechaSesion a asignar
	 */
	public void setFechaSesion(java.util.Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}
	/**
	 * Método de acceso al campo esPresente.
	 * @return El valor del campo esPresente
	 */
	public Boolean getEsPresente() {
		return esPresente;
	}
	/**
	 * Asigna el valor al campo esPresente.
	 * @param esPresente el valor esPresente a asignar
	 */
	public void setEsPresente(Boolean esPresente) {
		this.esPresente = esPresente;
	}
	/**
	 * Método de acceso al campo numeroSesion.
	 * @return El valor del campo numeroSesion
	 */
	public Short getNumeroSesion() {
		return numeroSesion;
	}
	/**
	 * Asigna el valor al campo numeroSesion.
	 * @param numeroSesion el valor numeroSesion a asignar
	 */
	public void setNumeroSesion(Short numeroSesion) {
		this.numeroSesion = numeroSesion;
	}
	/**
	 * Método de acceso al campo tipoSesion.
	 * @return El valor del campo tipoSesion
	 */
	public ValorDTO getTipoSesion() {
		return tipoSesion;
	}
	/**
	 * Asigna el valor al campo tipoSesion.
	 * @param tipoSesion el valor tipoSesion a asignar
	 */
	public void setTipoSesion(ValorDTO tipoSesion) {
		this.tipoSesion = tipoSesion;
	}
	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
	public ExpedienteDTO getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(ExpedienteDTO numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	
}
