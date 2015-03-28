/**
 * Nombre del Programa : ConsultarParentescosService.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 17-Oct-2011
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
package mx.gob.segob.nsjp.service.relacion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;

/**
 * 
 * @version 1.0
 * @author rgama
 */
public interface ConsultarParentescosService {

	/**
	 * Permite consular el catalogo de parentescos
	 * @return
	 * @throws NSJPNegocioException
	 */
    public List<CatRelacionDTO> consultarParentescos() throws NSJPNegocioException;


}
