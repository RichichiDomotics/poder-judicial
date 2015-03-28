package mx.gob.segob.nsjp.dao.sesion;

/**
* Nombre del Programa : SesionDAO.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad Sesion
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Sesion;


/**
 * Contrato de metodos de acceso a datos de la entidad Sesion.
 * @author rgama
 *
 */
public interface SesionDAO extends GenericDao<Sesion, Long> {
	/**
	 * Consulta todas las sesiones asociados a un Numero de Expediente
	 * @param aoExpediente
	 * 		Obligatorio <b>numeroExpedienteId</b>.
	 * @return List<Sesion>
	 * @throws NSJPNegocioException
	 */
    List<Sesion> consultarSesionesPorIdNumeroExpediente(Long aoNumeroExpedienteId);
    
    /**
     * Metodo que permite obtener el maximo numero de sesion por un numero de expediente
     * @param numeroExpedienteId
     * @return El maximo numero de sesion o 1 en caso de que no exista maximo
     */
	public Long obtenerSiguinteNumeroDeSesionPorNumeroExpediente(Long numeroExpedienteId);

}
