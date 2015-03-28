/**
 * Nombre del Programa : ConsultarPeritoPorEvidenciaServiceImpl.java
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
package mx.gob.segob.nsjp.service.funcionario.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.funcionario.ConsultarPeritoPorEvidenciaService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarPeritoPorEvidenciaServiceImpl
        implements ConsultarPeritoPorEvidenciaService {

    /**
     * Logger de la clase.
     */
    private final static Logger logger =
            Logger.getLogger(ConsultarPeritoPorEvidenciaServiceImpl.class);
    @Autowired
    private FuncionarioDAO funcionrioDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncionarioDTO consultarPeritoPorEvidencia(EvidenciaDTO evidenciaDto)
            throws NSJPNegocioException {
        if (evidenciaDto == null || evidenciaDto.getEvidenciaId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Consultando perito por evidencia = " + evidenciaDto);
        }
        FuncionarioDTO peritoAsociado = null;
        Evidencia evidencia =
                EvidenciaTransformer.transformarEvidencia(evidenciaDto);
        Funcionario perito =
                funcionrioDao.consultarFuncionarioPorEvidencia(evidencia);
        if (perito != null) {
            peritoAsociado =
                    FuncionarioTransformer.transformarFuncionario(perito);
        }
        return peritoAsociado;
    }
}
