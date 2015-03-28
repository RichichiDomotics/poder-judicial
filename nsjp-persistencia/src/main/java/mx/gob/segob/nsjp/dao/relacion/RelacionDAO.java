/**
* Nombre del Programa : RelacionDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos de acceso a datos de Relacion
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
package mx.gob.segob.nsjp.dao.relacion;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Relacion;

/**
 * Contrato para los metodos de acceso a datos de Relacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface RelacionDAO extends GenericDao<Relacion, Long> {

	/**
	 * Obtiene la relacion de acuerod al id del elemento y el tipo de relacion.
	 * @param idElemento
	 * @param tipoRelacion
	 * @return
	 */
	List<Relacion> obtenerRelacionSimple(Long idElemento, Long tipoRelacion);
	
	/**
	 * 
	 * @param elementoId
	 * @return
	 */
	public List<Relacion> consultarRelacionByElemento (Long elementoId);
	
	/**
	 * Consultar las relaciones asociadas con respecto a un elemento. La asociacion del elementos con otros
	 * puede ser por sujeto o complemento. 
	 * Es posoble hacer uso del filtro de Tipo Solicitud Implicita o Explicita.
	 * @param idElementos
	 * @param idTipoRelacion
	 * @return
	 */
	public List<Relacion> consultarRelacionByIdElementoAndTipoRelacion(List<Long> idElementos, Long idTipoRelacion);
	

    /**
     * Verifica si existe un registro en la tabla Relacion que corresponda
     * con los parametros {@code idElementoSujeto, idElementoComplemento y
     * idCatRelacion}.
     * @param idCatRelacion El tipo de relacion que se quiere verificar.
     * @param idElementoSujeto El id del elemento sujeto del que estamos
     * verificando en la tabla relacion.
     * @param idElementoComplemento El id del elemento complemento que estamos
     * verificando en la tabla relacion.
     * @return {@code true} en caso que exista un registro en la tabla relacion
     * que tenga alguna de las actividades asociadas que corresponda con el
     * {@code idActividad} que se le pasa como parametro. {@code false} en caso
     * que no exista un registro en la tabla relacion o que si existe, ninguna
     * de sus actividades relacionadas corresponda con el {@code idActividad}
     * pasado como parametro.
     */
    boolean existeRelacion(Long idCatRelacion,
            Long idElementoSujeto, Long idElementoComplemento);
}
