/**
* Nombre del Programa : SalaJAVSDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/11/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.audiencia;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SalaJAVS;

/**
 * Contrato para el acceso a datos de las sala de JAVS.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface SalaJAVSDAO extends GenericDao<SalaJAVS, Long> {

    /**
     * Recupera la sala JAVS asociada a una sala de audiencia
     * 
     * @param salaAudienciaId
     * @return
     */
	public SalaJAVS recuperarSalaJAVS(Long salaAudienciaId);
}
