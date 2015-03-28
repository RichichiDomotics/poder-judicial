/**
*
* Nombre del Programa : EntrevistaInicial.java                                    
* Autor                            : rgama                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                     
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de EntrevistaInicial entre la vista y servicios.                      
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




/**
 * @author rgama
 * @version 1.0
 */
public class EntrevistaInicialDTO extends SesionDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -117789523387532236L;
	private Boolean esVictimaDirecta;
	private String motivoAtencion;
	private String stringFecha;
	
	public EntrevistaInicialDTO(Long idObjeto) {
		super.setSesionId(idObjeto);
	}
	public EntrevistaInicialDTO() {
	}
	/**
	 * Método de acceso al campo esVictimaDirecta.
	 * @return El valor del campo esVictimaDirecta
	 */
	public Boolean getEsVictimaDirecta() {
		return esVictimaDirecta;
	}
	/**
	 * Asigna el valor al campo esVictimaDirecta.
	 * @param esVictimaDirecta el valor esVictimaDirecta a asignar
	 */
	public void setEsVictimaDirecta(Boolean esVictimaDirecta) {
		this.esVictimaDirecta = esVictimaDirecta;
	}
	/**
	 * Método de acceso al campo motivoAtencion.
	 * @return El valor del campo motivoAtencion
	 */
	public String getMotivoAtencion() {
		return motivoAtencion;
	}
	/**
	 * Asigna el valor al campo motivoAtencion.
	 * @param motivoAtencion el valor motivoAtencion a asignar
	 */
	public void setMotivoAtencion(String motivoAtencion) {
		this.motivoAtencion = motivoAtencion;
	}
	/**
	 * @return the stringFecha
	 */
	public String getStringFecha() {
		return stringFecha;
	}
	/**
	 * @param stringFecha the stringFecha to set
	 */
	public void setStringFecha(String stringFecha) {
		this.stringFecha = stringFecha;
	}
		
}
