/**
 * Nombre del Programa : ConsultarIndiceEstructuradoService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jul-2011
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
package mx.gob.segob.nsjp.service.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;

/**
 * 
 * @version 1.0
 * @author rgama
 */
public interface ConsultarIndicesEstructuradosService {

	/**
	 * Metodo que regresa una lista de IndicesEstructurado
	 * @param tipoOficioId: Permite filtrar por el tipo de Oficio, 
	 * 		  En caso de ser nullo consulta todos los indices
	 * @return List<IndiceEstructurado>
	 */
	public List<IndiceEstructuradoDTO> consultarIndicesEstructuradosXTipoOficio(Long tipoOficioId)
	throws NSJPNegocioException;

}
