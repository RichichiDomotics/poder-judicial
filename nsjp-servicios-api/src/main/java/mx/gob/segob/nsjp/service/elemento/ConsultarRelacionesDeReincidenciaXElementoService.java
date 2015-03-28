/**
 * Nombre del Programa : ConsultarRelacionesDeReincidenciaXElementoService.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-08-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
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
package mx.gob.segob.nsjp.service.elemento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;

/**
 * 
 * @version 1.0
 * @author rgama
 */
public interface ConsultarRelacionesDeReincidenciaXElementoService {

	/**
	 * Permite consultar las reincidencias de un Elemento(Vehiculo o persona)
	 * @param idElemento
	 * @return List<RelacionReincidenciaDTO> 
	 */
	public List<RelacionReincidenciaDTO> consultarReincidenciasXElemento(Long idElemento) throws NSJPNegocioException;

}
