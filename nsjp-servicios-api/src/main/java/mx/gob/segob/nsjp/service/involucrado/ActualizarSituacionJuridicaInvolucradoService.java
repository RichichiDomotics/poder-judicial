/**
 * Nombre del Programa : ActualizarSituacionJuridicaInvolucradoService.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato de servicio para actualizar la situacion juridica de un involucrado
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
package mx.gob.segob.nsjp.service.involucrado;

import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;

/**
 * Contrato de servicio para actualizar la situacion juridica de un involucrado.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public interface ActualizarSituacionJuridicaInvolucradoService {

    /**
     * Actualiza la situacion juridica de un involucrado, si esta es
     * sentenciado, se genera la carperta de ejecucion
     * 
     * @author cesarAgustin
     * @param involucradoDTO
     * @param situacionJuridica
     * @throws NSJPNegocioException
     */
    public Long actualizarSituacionJuridicaInvolucrado(
            InvolucradoDTO involucradoDTO, Long situacionJuridica, SentenciaDTO sentenciaDTO)
            throws NSJPNegocioException;
    /**
     * Obtiene la situacion juridica de un involucrado
     * 
     * @author cesarAgustin
     * @param involucradoDTO
     * @param situacionJuridica
     * @throws NSJPNegocioException
     */
    public Long obtenerSituacionJuridicaInvolucrado(
            InvolucradoDTO involucradoDTO)
            throws NSJPNegocioException;
    
    /**
     * Actualiza la situación juridica de un invlucrado sin aplicar ninguna otra
     * regla de negocio.
     * 
     *@param involucradoDTO requerido: <b>elementoId</b>.
     * @param situacionJuridica
     * @return
     * @throws NSJPNegocioException
     */
    public void actualizarSituacionJuridicaDefensoria(
            InvolucradoDTO involucradoDTO, SituacionJuridica situacionJuridica)
            throws NSJPNegocioException;
}
