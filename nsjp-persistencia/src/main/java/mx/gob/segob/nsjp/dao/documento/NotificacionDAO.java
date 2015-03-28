/**
 * Nombre del Programa : NotificacionDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de Acceso a Datos para la notificacion.
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
package mx.gob.segob.nsjp.dao.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Objeto de Acceso a Datos para la notificacion.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface NotificacionDAO extends GenericDao<Notificacion, Long> {
    /**
     * 
     * @param audienciaId
     * @param invoId
     * @return
     */
    List<Notificacion> consultarNotificacionesPorAudienciaInvolucrado(
            Long audienciaId, Long invoId);


    /**
     * Consulta las notificaciones de un funcionario que esten en un estatus
     * especifico. Hace el paginado de las notificaciones para no consultar
     * todas al mismo tiempo.
     * @param funcionario El funcionario del que se buscaran las notificaciones
     * asociadas.
     * @param estatus El estatus de las notificaciones que se estan
     * buscando.
     * @return Una lista de las notificaciones asociadas a la busqueda. La
     * lista vacia en caso que no existan notificaciones del usuario en el
     * estatus requerido.
     */
    List<Notificacion> consultarNotificacionesXUsuario(Funcionario funcionario,
            Valor estatus, int pagina, int numeroDeRegistros,
            String campoOrden, int direccionOrden);

    long consultarNumeroTotalDeNotificacionesXUsuario(Funcionario funcionario, Valor estatus);

    List<Notificacion> consultarNotificacionesAlmacen(Almacen almacen,
            long tipoMovimiento, long estadoNotificacion);

    /**
     * Obtiene el último folio asignado a una notificación
     * @return
     * @throws NSJPNegocioException
     */
	public String obtenerUltimoFolioNotificacion() throws NSJPNegocioException;
	
	List<Notificacion> consultarNotificacionesXFuncionario(
			Long claveFuncionario, Long idCampo);

	/**
	 * Obtiene cuantas notificaciones existen
	 * @return
	 */
	Long obtenerMaximaNotificacion();

	/**
	 * Consulta las notificaciones para un funcionario que se encuentra registrado en una audiencia
	 * @param id
	 * @param claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<Notificacion> consultarNotificacionesPorAudienciaFuncionario(Long id,
			Long claveFuncionario)throws NSJPNegocioException;

}
