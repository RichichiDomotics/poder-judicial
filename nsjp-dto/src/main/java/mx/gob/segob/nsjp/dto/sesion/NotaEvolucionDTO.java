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

import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * 
 * @author rgama
 * @version 1.0
 */
public class NotaEvolucionDTO extends SesionDTO {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 358517356388521877L;
	
	private String seguimiento;
	private String objetivo;
	private String analisisSesion;
	private String planteamientoTerap;
	private String obsFaltaInteres;
	private InvolucradoDTO victima;
	private String stringFecha;
	
	public NotaEvolucionDTO(Long idObjeto) {
		super.setSesionId(idObjeto);
	}
	public NotaEvolucionDTO() {
	}
	/**
	 * Método de acceso al campo seguimiento.
	 * @return El valor del campo seguimiento
	 */
	public String getSeguimiento() {
		return seguimiento;
	}
	/**
	 * Asigna el valor al campo seguimiento.
	 * @param seguimiento el valor seguimiento a asignar
	 */
	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}
	/**
	 * Método de acceso al campo objetivo.
	 * @return El valor del campo objetivo
	 */
	public String getObjetivo() {
		return objetivo;
	}
	/**
	 * Asigna el valor al campo objetivo.
	 * @param objetivo el valor objetivo a asignar
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	/**
	 * Método de acceso al campo analisisSesion.
	 * @return El valor del campo analisisSesion
	 */
	public String getAnalisisSesion() {
		return analisisSesion;
	}
	/**
	 * Asigna el valor al campo analisisSesion.
	 * @param analisisSesion el valor analisisSesion a asignar
	 */
	public void setAnalisisSesion(String analisisSesion) {
		this.analisisSesion = analisisSesion;
	}
	/**
	 * Método de acceso al campo planteamientoTerap.
	 * @return El valor del campo planteamientoTerap
	 */
	public String getPlanteamientoTerap() {
		return planteamientoTerap;
	}
	/**
	 * Asigna el valor al campo planteamientoTerap.
	 * @param planteamientoTerap el valor planteamientoTerap a asignar
	 */
	public void setPlanteamientoTerap(String planteamientoTerap) {
		this.planteamientoTerap = planteamientoTerap;
	}
	/**
	 * Método de acceso al campo obsFaltaInteres.
	 * @return El valor del campo obsFaltaInteres
	 */
	public String getObsFaltaInteres() {
		return obsFaltaInteres;
	}
	/**
	 * Asigna el valor al campo obsFaltaInteres.
	 * @param obsFaltaInteres el valor obsFaltaInteres a asignar
	 */
	public void setObsFaltaInteres(String obsFaltaInteres) {
		this.obsFaltaInteres = obsFaltaInteres;
	}
	/**
	 * Método de acceso al campo victima.
	 * @return El valor del campo victima
	 */
	public InvolucradoDTO getVictima() {
		return victima;
	}
	/**
	 * Asigna el valor al campo victima.
	 * @param victima el valor victima a asignar
	 */
	public void setVictima(InvolucradoDTO victima) {
		this.victima = victima;
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
