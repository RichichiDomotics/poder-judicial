/**
 * Nombre del Programa : ConsultarActuacionesPorCatDelitosService.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24/08/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Define el contrato para el servicio de canalizar causa
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
package mx.gob.segob.nsjp.service.actividad;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Define el contrato para el servicio  ConsultarActuacionesPorCatDelitosService
 * @author rgama
 *
 */
public interface ConsultarActuacionesPorCatDelitosService{

	/**
	 * Permite consultar las Actividades asociadas a un conjunto de CatDelitos
	 * @param idsCatDelito: Lista de los identificadores de CatDelito
	 * @return List<ValorDTO>
	 * @throws NSJPNegocioException
	 */
	List<ValorDTO> consultarActividadesPorIdsCatDelito(List<Long> idsCatDelito) throws NSJPNegocioException;
	
}
