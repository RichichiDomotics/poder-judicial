/**
 * Nombre del Programa : CadenaDeCustodiaDelegate.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-jul-2011
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
package mx.gob.segob.nsjp.delegate.cadenadecustodia;


import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de CadenaDeCustodia
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface CadenaDeCustodiaDelegate {

    /**
     * Consulta la cadena de custodia asociada al folio
     * Los datos que consulta son:
     * - Número de la evidencia
     * - Información de la evidencia
     * - Último eslabón asociado
     * - Número de eslabón
     * - Tipo de eslabón
     * - Almacén
     * @param folio El folio de la cadena de custodia.
     * @return Un objeto de tipo CadenaDeCustodia, en caso que no exista uno
     * asociado al folio, se regresa NULL.
     * @throws NSJPNegocioException En caso que {@code folio} sea null.
     */
    CadenaDeCustodiaDTO consultarCadenaCustodiaXFolio(String folio) throws NSJPNegocioException;

    /**
     * Operación que realiza la funcionalidad de consultar las cadenas de
     * custodia relacionadas al expediente que recibe.
     * @param expedienteDto Un expediente del que buscara su cadena de custodia
     * asociada.
     * @return La cadena de custodia asociada al expediente o {@code null} en
     * caso que el expedienteDto buscado no exista o no tenga una cadena de
     * custodia asociada.
     * @throws NSJPNegocioException En caso de recibir un expedienteDto
     * {@code null} o en caso que el expedienteDto no tenga un id por el cual
     * buscar.
     */
    List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXExpediente(
            ExpedienteDTO expedienteDto) throws NSJPNegocioException;
    /**
     * Operación que realiza la funcionalidad de guardar y actualizar la cadena de custodia y la funcionalidad de asociar la cadena de custodia a un expediente.
     * @param custodiaDTO: Objeto
     * @param expedienteDTO: Id Expediente
     * @return
     * @throws NSJPNegocioException
     */
    public CadenaDeCustodiaDTO guardarCadenaCustodia(CadenaDeCustodiaDTO custodiaDTO, ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
    /**
     * Operación que realiza la funcionalidad de consultar una cadena de custodia determinada por su id.
     * @param cadenaDTO
     * @return
     * @throws NSJPNegocioException
     */
    CadenaDeCustodiaDTO consultarCadenaCustodia(CadenaDeCustodiaDTO cadenaDTO)throws NSJPNegocioException;
    
    /**
     * Operación que realiza la funcionalidad de consultar una cadena de custodia determinada por número de expediente
     * @author Marco Gallardo
     * @param cadenaDTO
     * @return 
     * @throws NSJPNegocioException
     */
    public List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXNumeroExpediente (Long expedienteId, String folioCadena)throws NSJPNegocioException;
    
    /**
     * Operación que permite consultar las evidencias que se encuentran asignadas a un almacen dado
     * @param almacenDTO: idAlmacen
     * @param casoDTO: idCaso (Opcional)
     * @return
     * @throws NSJPNegocioException
     */
    List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXAlmacen(AlmacenDTO almacenDTO, CasoDTO casoDTO) throws NSJPNegocioException;
    
}
