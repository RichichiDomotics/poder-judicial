/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.segob.nsjp.delegate.cadenadecustodia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;

/**
 *
 * @author sawbona
 */
public interface ConsultarCadenaDeCustodiaXFolioDelegate {

    /**
     * Operación que realiza la funcionalidad de consultar la cadena de custodia
     * asociada al folio
     * Los datos que consulta son:
     * - Número de la evidencia 
     * - Información de la evidencia
     * - Último eslabón asociado
     * - Número de eslabón
     * - Tipo de eslabón
     * - Almacén
     * Devuelve 
     * @param folio El folio de la cadena de custodia.
     * @return Un objeto de tipo CadenaDeCustodiaDTO con la informacion
     * mencionada, en caso que el folio buscado no existaregresa NULL.
     * @throws NSJPNegocioException 
     */
    CadenaDeCustodiaDTO consultarCadenaDeCustodiaXFolio(String folio) throws NSJPNegocioException;

}
