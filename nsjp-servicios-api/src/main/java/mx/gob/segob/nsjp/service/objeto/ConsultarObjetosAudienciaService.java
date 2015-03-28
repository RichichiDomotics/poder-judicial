/**
 * Nombre del Programa  : ConsultarObjetosAudienciaService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 28 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Consulta los objetos registrados para una audiencia
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

import java.util.List;

import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;

public interface ConsultarObjetosAudienciaService {

	List<EvidenciaDTO> consultarObjetosEnAudiencia(EventoDTO input);

}
