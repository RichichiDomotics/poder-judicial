/**
 * Nombre del Programa : RegistrarRelacionDeReincidenciaService.java
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
public interface RegistrarRelacionDeReincidenciaService {

	/**
	 * Permite registrar las reincidencias de un Elemento(Vehiculo o persona)
	 * @param idElemento
	 * @param idCasos
	 * @throws NSJPNegocioException
	 */
	public List<RelacionReincidenciaDTO> registrarReinicidencias(Long idElemento,
			List<Long> idCasos, Long idFuncionario) throws NSJPNegocioException;

}
