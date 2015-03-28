/**
* Nombre del Programa : JerarquiaOrgTipoSolicitudDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16/08/2011
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
package mx.gob.segob.nsjp.dao.institucion;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.JerarquiaOrgTipoSolicitud;
import mx.gob.segob.nsjp.model.Valor;

/**
 * DAO para la entidad de Cruce entre Jerarquia Organizacional 
 * y Tipo Solicitud 
 *  
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface JerarquiaOrgTipoSolicitudDAO extends
		GenericDao<JerarquiaOrgTipoSolicitud, Long> {

	/**
	 * Consulta de los Tipos de solicitudes de acuerdo al ID de la Jerarquia
	 * Organizacional. La recuperación de la información se realiza mediante 
	 * la tabla de cruce de JerarquiaOrgTipoSolicitud
	 * 
	 * @param jerarquiaOrganizacionalId
	 * @return
	 */
	public List<Valor> consultarTipoSolicitudPorJerarquiaOrganizacional(
			Long jerarquiaOrganizacionalId);
	
}
