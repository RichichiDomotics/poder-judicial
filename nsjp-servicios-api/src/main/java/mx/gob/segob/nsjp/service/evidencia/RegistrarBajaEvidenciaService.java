    /**
 * Nombre del Programa : RegistrarBajaEvidenciaService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-ago-2011
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
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface RegistrarBajaEvidenciaService {

    void registrarBajaEvidencia(List<EvidenciaDTO> evidenciasDto)
            throws NSJPNegocioException;

    /**
     * Consulta un funcionario por su nombre, apellido paterno, materno,
     * nombre de la institucion y area.
     * @param criterioDto Objeto que contiene la informacion para hacer la
     * consulta.
     * @return El funcionario asociado o null en caso de no existir un
     * funcionario que coincida con la informacion buscada.
     * @throws NSJPNegocioException
     */
    FuncionarioDTO consultaFuncionarioPorNombreInstitucionYPuesto(FuncionarioDTO
            criterioDto) throws NSJPNegocioException;

    /**
     * Elimina fisicamente la evidencia y da de baja lógica el objeto asociado a dicha evidencia.
     * 
     * @param idEvidencia a ser eliminada
     * @return Boleano que indica si la eliminación se realizó con éxito.
     * @throws NSJPNegocioException
     */
    Boolean eliminarEvidencia(Long idEvidencia) throws NSJPNegocioException;
}
