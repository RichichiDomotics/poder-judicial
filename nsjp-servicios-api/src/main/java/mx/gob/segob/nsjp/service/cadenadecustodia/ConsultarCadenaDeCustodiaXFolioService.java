/**
 * Nombre del Programa : ConsultarCadenaDeCustodiaXFolioService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jun-2011
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
package mx.gob.segob.nsjp.service.cadenadecustodia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;

/**
 *
 * @author Jacob Lobaco
 */
public interface ConsultarCadenaDeCustodiaXFolioService {

    public CadenaDeCustodiaDTO consultarCadenaDeCustodiaXFolio(String folio) throws NSJPNegocioException;

    /**
     * Consulta una cadena de custodia de acuerdo a un numero de expediente y un folioi de cadena
     * @param folio
     * @param idExpediente
     * @return
     */
	public CadenaDeCustodiaDTO consultarCadenaDeCustodiaPorFolioExpediente(
			String folio, Long idExpediente) throws NSJPNegocioException;

}
