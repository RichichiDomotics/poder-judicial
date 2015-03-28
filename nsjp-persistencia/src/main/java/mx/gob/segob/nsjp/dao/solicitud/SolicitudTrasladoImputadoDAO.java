/**
* Nombre del Programa : SolicitudTrasladoImputadoDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad SolicitudTrasladoImputado
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
package mx.gob.segob.nsjp.dao.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;

/**
 * Contrato de metodos de acceso a datos de la entidad SolicitudTrasladoImputado.
 * @author cesarAgustin
 *
 */
public interface SolicitudTrasladoImputadoDAO extends
		GenericDao<SolicitudTrasladoImputado, Long> {

	/**
	 * 
	 * @param valorId
	 * @return
	 */
	List<SolicitudTrasladoImputado> consultarSolicitudesTrasladosImputadoByEstatus(
			Long estatusSolicitud);

}
