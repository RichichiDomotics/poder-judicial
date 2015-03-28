/**
* Nombre del Programa : AsignacionCentroDetencionDAO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/01/2012
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
package mx.gob.segob.nsjp.dao.programa;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.AsignacionCentroDetencion;

import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de AsignacionCentroDetencion
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public interface AsignacionCentroDetencionDAO extends GenericDao<AsignacionCentroDetencion, Long> {
		
	/**
	 * M&eacute;todo que consulta una AsignacionCentroDetencion por id
	 * @return AsignacionCentroDetencion
	 * @throws NSJPNegocioException
	 */
	public AsignacionCentroDetencion consultarAsignacionCentroDetencionPorId(AsignacionCentroDetencion asignacionCentroDetencion)throws NSJPNegocioException;
		
}
