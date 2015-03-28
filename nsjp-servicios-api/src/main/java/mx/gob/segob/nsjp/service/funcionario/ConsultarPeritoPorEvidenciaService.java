/**
 * Nombre del Programa : ConsultarPeritoPorEvidenciaService.java
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
package mx.gob.segob.nsjp.service.funcionario;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarPeritoPorEvidenciaService {

    /**
     * Consulta el Perito (Funcionario) asociado a la evidencia.
     * @param evidenciaDto La evidencia de la que se busca su funcionario.
     * @return El Perito (Funcionario) asociado a la evidencia o {@code null} en
     * caso que la evidencia buscada no exista o que no tenga un Perito
     * (Funcionario) asociado.
     * @throws NSJPNegocioException En caso que alguno de {@code evidenciaDto} o
     * {@code evidenciaDto.evidenciaId} sea {@code null}.
     */
    FuncionarioDTO consultarPeritoPorEvidencia(EvidenciaDTO evidenciaDto)
            throws NSJPNegocioException;

}
