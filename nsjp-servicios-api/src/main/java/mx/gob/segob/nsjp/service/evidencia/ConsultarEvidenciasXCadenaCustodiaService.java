/**
 * Nombre del Programa : ConsultarEvidenciasXCadenaCustodiaService.java
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
package mx.gob.segob.nsjp.service.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarEvidenciasXCadenaCustodiaService {

    /**
     * Consulta las evidencias asociadas a una cadena de custodia.
     * @param cadenaDeCustodiaDTO Objeto con el número de folio de la
     * cadena de custodia.
     * @return Devuelve el listado de evidencias asociadas a la cadena de
     * custodia. En caso de no existir una cadena de custodia con el numero de
     * folio o en caso de no existir evidencias asociadas a la cade de custodia,
     * regresa la lista vacia.
     * <ol>
     * De cada evidencia se consultan los siguientes datos:
     * <li> Identificador de la evidencia
     * <li> Información de la evidencia
     * <li> Hora del levantamiento de la evidencia
     * <li> Origen de la evidencia
     * <li> Último eslabón asociado
     * <li> Número de eslabón
     * <li> Tipo de eslabón
     * <li> Almacén (Dentro de Evidencia.Objeto.Almacen)
     * </ol>
     * @throws NSJPNegocioException En caso de recibir una
     * {@code cadenaDeCustodiaDTO} nula o en caso de no contener un numero de
     * folio
     */
    List<EvidenciaDTO> consultarEvidenciasXCadenaCustodia(
            CadenaDeCustodiaDTO cadenaDeCustodiaDTO) throws NSJPNegocioException;
    
    /**
     * Realiza una consulta de las evidencias asignadas a una cadena de custodia
     * que están implicadas en una solicitud pericial.
     * Por cada evidencia verifica si está asignada a alguna de las solicitudes hijas,
     * si está asignada entonces en el funcionario que recibe del último eslabón
     * de la evidencia será el perito de la solicitu hija donde está asignada
     * la evidencia (solo en el DTO para fines de mostrarlo en pantalla)
     * @param cadena Cadena de custodia para la cuál consultar las evidencias
     * @param solicitud Solicitud inicial de pericial sobre la cuál se está trabajando
     * @return Lista de evidencias encontradas y ajustadas según los peritos de la solicitud
     * @throws NSJPNegocioException
     */
    List<EvidenciaDTO> consultarEvidenciaXCadenaCustodiaYPeritoDeSolicitudPericial(CadenaDeCustodiaDTO cadena,SolicitudPericialDTO solicitud) throws NSJPNegocioException;

    /**
     * Obtiene una lista de peritos asignados a una solicitud inicial que no tengan asignación como 
     * último eslabón de la cadena de custodia
     * @param cadena Cadena de custodia a tomar en cuenta para las asignaciones
     * @param solicitud Solicitud incial que tiene solicitudes hijas con peritos asignados
     * @return Lista de peritos no asignados
     * @throws NSJPNegocioException
     */
    List<FuncionarioDTO> consultarPeritosSinAsignacionEnCadenaDeCustodia(CadenaDeCustodiaDTO cadena,SolicitudPericialDTO solicitud) throws NSJPNegocioException;
}
