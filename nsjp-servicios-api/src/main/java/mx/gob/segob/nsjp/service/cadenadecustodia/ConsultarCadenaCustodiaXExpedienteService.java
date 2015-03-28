/**
 * Nombre del Programa : ConsultarCadenaCustodiaXExpedienteService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarCadenaCustodiaXExpedienteService {

    /**
     * Operación que realiza la funcionalidad de consultar las cadenas de
     * custodia relacionada al expediente que recibe.
     * @param expedienteDto Un expediente del que buscara su cadena de custodia
     * asociada.
     * @return La cadena de custodia asociada al expediente o {@code null} en
     * caso que el expedienteDto buscado no exista o no tenga una cadena de
     * custodia asociada.
     * @throws NSJPNegocioException En caso de recibir un expedienteDto
     * {@code null} o en caso que el expedienteDto no tenga un id por el cual
     * buscar.
     */
    public List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXExpediente(
            ExpedienteDTO expedienteDto) throws NSJPNegocioException;

    /**
     * Operación que realiza la funcionalidad de consultar una cadena de custodia determinada por su id.
     * @param cadenaDTO
     * @return
     * @throws NSJPNegocioException
     */
	public CadenaDeCustodiaDTO consultarCadenaCustodia(
			CadenaDeCustodiaDTO cadenaDTO)throws NSJPNegocioException;
	
	/**
	 * 
	 * @version 1.0
	 * @author Marco Gallardo
	 *      * Operación que realiza la funcionalidad de consultar una cadena de custodia determinada por número de expediente.
     * @param cadenaDTO
     * @return List<CadenaDeCustodiaDTO>
     * @throws NSJPNegocioException
	 */
	
	public List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXExpedienteYFolio(Long expedienteId, String folioCadena)throws NSJPNegocioException;	

}
