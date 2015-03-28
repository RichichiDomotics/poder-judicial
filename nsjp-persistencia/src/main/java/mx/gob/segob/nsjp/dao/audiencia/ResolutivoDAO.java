/**
 * Nombre del Programa  : ResolutivoDAO.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Administra la persistencia y acceso a los datos de los objetos de tipo resolutivo.
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
package mx.gob.segob.nsjp.dao.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Resolutivo;

public interface ResolutivoDAO extends GenericDao<Resolutivo, Long> {

	List<Resolutivo> consultarResolutivosByAudienciaId(Long idAudiencia);
	
	List<Resolutivo> consultarResolutivosByAudienciaIdWithDocumento(Long idAudiencia);

	List<Resolutivo> consultarResolutivosByAudienciaIdWithDocumentoAndArchDigital(Long idAudiencia);
}
