/**
 * Nombre del Programa  : RegistrarTestigoEnAudiencia.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un involucrado con calidad de Testigo en la audiencia
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
package mx.gob.segob.nsjp.service.involucrado;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Registra la asistencia de un involucrado con Calidad de TESTIGO a una Audiencia determinada.
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface RegistrarTestigoEnAudienciaService {
	
    /**
     * Registra la asistencia de un <b>testigo</b> a una Audiencia determinada
     * por el parametro <b>audiencia</b>.
     * 
     * @param input
     */
	public void registrarTestigoEnAudiencia(AudienciaDTO audiencia, InvolucradoDTO testigo) throws NSJPNegocioException;
}
