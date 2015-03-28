/**
 * Nombre del Programa : CentroDetencionService.java
 * Autor                            : Cuauhtemoc Paredes Serrano
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-Sept-2011
 * Marca de cambio        : N/A
 * Descripcion General    : Consulta los centros de detencion
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.detencion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;

public interface CentroDetencionService {

	public CentroDetencionDTO consultarCentroDetencion(Long centroDetencionId)throws NSJPNegocioException;

	List<CentroDetencionDTO> consultarCentrosDetencionPorTipo(
			Long tipoCentroDetencion) throws NSJPNegocioException;

}
