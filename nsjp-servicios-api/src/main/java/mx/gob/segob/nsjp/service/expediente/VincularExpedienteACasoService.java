/**
 * Nombre del Programa : VincularExpedienteACasoService.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 6 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para metodos del servicio de asignacion de un expediente a un caso
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
package mx.gob.segob.nsjp.service.expediente;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Contrato para metodos del servicio de asignacion de un expediente a un caso.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public interface VincularExpedienteACasoService {

    /**
     * Consulta los expedientes del sistema que sean similares o iguales a la
     * cadena numeroGeneralCaso
     * 
     * @param numeroGeneralCaso
     * @return
     */
    public List<CasoDTO> consultarCasos(String numeroGeneralCaso)
            throws NSJPNegocioException;

    /**
     * Vincula un <code> expediente </code> a un <code> caso </code>
     * 
     * @param expediente
     *            Expediente sin caso. <b>Obligatorio</b>.
     * @param caso
     *            Caso.<b>Obligatorio</b>.
     * @param involucradoPG
     *            Involucrado de PG que se debe asociar a DefensaInvolucrado.
     *            Opcional. <b>Aplica solo para Defensoría</b>.
     * @throws NSJPNegocioException
     */
    void vincularExpedienteACaso(ExpedienteDTO expediente, CasoDTO caso,
            InvolucradoDTO involucradoPG) throws NSJPNegocioException;

}
