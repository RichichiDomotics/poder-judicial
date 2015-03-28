/**
* Nombre del Programa : FiltroIndividuoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para enviar los parametros de consulta de Individuo, al servicio
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

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO para enviar los parametros de consulta de Individuo, al servicio.
 * @version 1.0
 * @author cesarAguston
 *
 */
public class FiltroIndividuoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2838834989560103229L;
	
	private String numeroExpediente;
	private Calidades calidadIndividuo;
	
	
	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	/**
	 * Método de acceso al campo calidadIndividuo.
	 * @return El valor del campo calidadIndividuo
	 */
	public Calidades getCalidadIndividuo() {
		return calidadIndividuo;
	}
	/**
	 * Asigna el valor al campo calidadIndividuo.
	 * @param calidadIndividuo el valor calidadIndividuo a asignar
	 */
	public void setCalidadIndividuo(Calidades calidadIndividuo) {
		this.calidadIndividuo = calidadIndividuo;
	}
	
}
