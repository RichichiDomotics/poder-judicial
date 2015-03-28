/**
 * Nombre del Programa  : RegistrarObjetoEnAudienciaService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un ojeto en la audiencia
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.objeto;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
/**
 * Registra la presentación de un OBJETO en una Audiencia determinada.
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface RegistrarObjetoEnAudienciaService {

	/**
	 * Registra la presencia de un <b>objeto</b> a una Audiencia determinada por
	 * el parametro <b>audiencia</b>.
	 * 
	 * @param input
	 */
	public void registrarObjetoEnAudiencia(Long audienciaId, Long insitutcion,
			String descripcion, Long condicionFisica, String noCustodia,
			Long noPrueba) throws NSJPNegocioException;
}
