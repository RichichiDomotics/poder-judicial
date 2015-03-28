/**
 * Nombre del Programa  : RegistrarResolutivoAudienciaService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un resolutivo en la audiencia
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
package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;

/**
 * Registra las resoluciones generadas en la audiencia
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface RegistrarResolutivoAudienciaService {

	public Long registrarResolutivoAudiencia(ResolutivoDTO resolutivo) throws NSJPNegocioException;

	public void modificarResolutivoAudiencia(ResolutivoDTO resolutivo);

	public void eliminarResolutivoAudiencia(ResolutivoDTO resolutivo);
	
}
